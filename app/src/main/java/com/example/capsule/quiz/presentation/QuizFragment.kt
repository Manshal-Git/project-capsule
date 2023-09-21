package com.example.capsule.quiz.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.capsule.databinding.FragmentQuizBinding
import com.example.capsule.utils.extension.changeFragment

/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {

    lateinit var binding: FragmentQuizBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(layoutInflater)
        changeFragment(
            QuizSessionFragment.newInstance(),
            containerId = binding.root.id
        )
        return binding.root
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
            QuizFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}