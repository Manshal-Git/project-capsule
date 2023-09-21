package com.example.capsule.utils.extension

import android.os.Bundle
import androidx.fragment.app.Fragment

fun Fragment.changeFragment(
    fragment: Fragment,
    transition : Int? = null,
    extras: (() -> Bundle)? = null,
    containerId : Int
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