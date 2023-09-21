package com.example.assignmentproject

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter


class CapsulePagerAdapter(
    private val context: Context,
    private val fragments : List<Fragment>,
    fm: FragmentManager,
) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return fragments.size
    }

    companion object {
        private val TAB_TITLES = arrayOf(
            R.string.video,
            R.string.notes,
            R.string.quiz
        )
    }
}