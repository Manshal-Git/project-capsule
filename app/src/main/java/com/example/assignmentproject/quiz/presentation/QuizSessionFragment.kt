package com.example.assignmentproject.quiz.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.assignmentproject.R
import com.example.assignmentproject.data.Response
import com.example.assignmentproject.databinding.FragmentQuizSessionBinding
import com.example.assignmentproject.utils.extension.changeFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import kotlin.time.Duration.Companion.seconds

/**
 * A simple [Fragment] subclass.
 * Use the [QuizSessionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizSessionFragment : Fragment() {

    lateinit var binding: FragmentQuizSessionBinding
    private val optionButtons by lazy {
        arrayListOf(
            binding.btnOne,
            binding.btnTwo,
            binding.btnThree,
            binding.btnFour
        )
    }
    private val viewModel: QuizViewModel = get()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizSessionBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.questions.observe(viewLifecycleOwner) {
            when (it) {
                is Response.Error -> {
                }

                is Response.Loading -> {
                }

                is Response.Success -> {
                    viewModel.showNextQuiz()
                }
            }
        }

        viewModel.currentQuiz.observe(viewLifecycleOwner) {
            setUpQuizNo(it.id)
            setUpQuestion(it.question)
            setUpOptions(it.options)
        }

        optionButtons.forEach { button ->
            button.setOnClickListener {
                onOptionClick(it)
            }
        }

        viewModel.getQuizForChapter()
    }

    private fun setUpQuizNo(id: Int) {
        binding.apply {
            tvQuestionCount.text = getString(R.string.quizCount,id,5)
            tvQuestionNo.text = "Q.$id"
        }
    }

    private fun setUpOptions(options: List<String>) {
        for (i in 0..3)
            optionButtons[i].text = options[i]
    }

    private fun setUpQuestion(question: String) {
        binding.tvQuestion.text = question
    }

    private fun onOptionClick(v: View) {
        val correctAnswer = viewModel.currentQuiz.value?.correctAnswer!!
        val selectedAnswer = (v as Button).text as String
        highlightAnswer(correctAnswer, selectedAnswer)
        lifecycleScope.launch {
            // delay to show highlighted answer for a second then go for next quiz
            delay(1000)
            try {
                viewModel.showNextQuiz()
            } catch (e : ArrayIndexOutOfBoundsException){
            changeFragment(
                QuizResultFragment.newInstance(),
                containerId = R.id.quizFragmentContainer,
                extras = {
                    Bundle().apply {
                        putInt(SOLVED,2)
                        putInt(TOTAL,5)
                    }
                },
                transition = FragmentTransaction.TRANSIT_FRAGMENT_OPEN
            )
            Toast.makeText(requireContext(), "Complete", Toast.LENGTH_SHORT).show()
        }
    }
}

fun highlightAnswer(correctAns: String, selectedAns: String) {
    // highlight the correct answer with green
    optionButtons.find { it.text == correctAns }?.let { btn -> changeColor(btn, R.color.TintGreen) }

    if (selectedAns != correctAns) {
        // highlight the selected answer with as wrong
        optionButtons.find { it.text == selectedAns }
            ?.let { btn -> changeColor(btn, R.color.TintRed) }
    }
}

private fun changeColor(v: View, colorId: Int) {
    lifecycleScope.launch {
        v.backgroundTintList = AppCompatResources.getColorStateList(requireContext(), colorId)
        delay(1.seconds)
        v.backgroundTintList =
            AppCompatResources.getColorStateList(requireContext(), R.color.white)
    }
}

companion object {
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     */
    @JvmStatic
    fun newInstance() =
        QuizSessionFragment().apply {
            arguments = Bundle().apply {

            }
        }
}
}