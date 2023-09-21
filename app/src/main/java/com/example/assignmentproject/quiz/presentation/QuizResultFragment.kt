package com.example.assignmentproject.quiz.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import com.example.assignmentproject.R
import com.example.assignmentproject.databinding.FragmentQuizResultBinding

const val SOLVED = "solved"
const val TOTAL = "total"

/**
 * A simple [Fragment] subclass.
 * Use the [QuizResultFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizResultFragment : Fragment() {

    lateinit var binding: FragmentQuizResultBinding
    var solved = 0
    var totalQuiz = 0
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
            solved < 3 -> "Need Revision !"
            solved > 3 -> "Well done !"
            else -> "You can Improve !"
        }

        val resultColorId = when {
            solved < 3 -> R.color.fadeRed
            solved > 3 -> R.color.fadeGreen
            else -> R.color.smooth_blue
        }

        binding.root.backgroundTintList = AppCompatResources.getColorStateList(
            requireContext(), resultColorId
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