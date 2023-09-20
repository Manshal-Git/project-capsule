package com.example.assignmentproject.utils.extension

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.assignmentproject.R

fun Fragment.changeFragment(
    fragment: Fragment,
    transition : Int? = null,
    extras: (() -> Bundle)? = null,
    containerId : Int = R.id.bottomFrame  // Self container id is Default
) {
    parentFragmentManager
        .beginTransaction()
        .run {
            transition?.let { setTransition(it) }
            this
        }
        .replace(containerId, fragment.apply {
            if (extras != null) {
                arguments = extras()
            }
        }
        ).commit()

}