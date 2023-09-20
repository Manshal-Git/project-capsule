package com.example.assignmentproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.assignmentproject.databinding.WhatIsNextButtonLayoutBinding

/**
 * A simple [Fragment] subclass.
 * Use the [NextButtonFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NextButtonFragment private constructor(): Fragment() {

    lateinit var binding: WhatIsNextButtonLayoutBinding
    var title : String? = null
    var description : String? = null
    var selectedTabIndex : Int = 0
    lateinit var onClick : (Int) -> Unit

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = WhatIsNextButtonLayoutBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            tvTitle.text = title
            tvDescription.text = description
            nextButton.setOnClickListener{
                onClick(selectedTabIndex+1)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment VideoFragment.
         */

        @JvmStatic
        fun newInstance(title : String,subTitle : String,selectedTabIndex : Int,changeTab : (Int) -> Unit) =
            NextButtonFragment().apply {
                this.title = title
                description = subTitle
                this.selectedTabIndex = selectedTabIndex
                onClick = changeTab
            }
    }
}