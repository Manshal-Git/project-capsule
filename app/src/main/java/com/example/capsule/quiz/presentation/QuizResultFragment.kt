package com.example.capsule.quiz.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import com.example.capsule.R
import com.example.capsule.databinding.FragmentQuizResultBinding
import com.example.capsule.utils.extension.hide
import com.example.capsule.utils.extension.show
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val SOLVED = "solved"
const val TOTAL = "total"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizResultFragment : Fragment() {

    lateinit var binding: FragmentQuizResultBinding
    private var solved = 0
    private var totalQuiz = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        solved = arguments?.getInt(SOLVED)!!
        totalQuiz = arguments?.getInt(TOTAL)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizResultBinding.inflate(layoutInflater)
        // Artificial processing flow
        lifecycleScope.launch {
            binding.loadingLayout.show()
            binding.resultLayout.hide()
            delay(1500)
            binding.loadingLayout.hide()
            binding.resultLayout.show()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSolved()
        setUpResultSummary()
        binding.btnComplete.setOnClickListener{
            requireActivity().finish()
        }
    }

    private fun setUpResultSummary() {
        binding.tvResultSummary.text = when {
            solved < 3 -> "Need Revision ! 👍"
            solved > 3 -> "Well done ! ✌"
            else -> "You can Improve ! 🤞"
        }

        val resultColorId = when {
            solved < 3 -> R.color.TintRed
            solved > 3 -> R.color.TintGreen
            else -> R.color.solid_blue
        }

        binding.tvResultSummary.setTextColor(
            AppCompatResources.getColorStateList(requireActivity(),resultColorId)
        )
    }

    private fun setUpSolved() {
        binding.solvedQuestions.text = getString(R.string.solved_messege, solved, totalQuiz)
    }

    companion object {
        @JvmStatic
        fun newInstance() = QuizResultFragment().apply {}
    }
}