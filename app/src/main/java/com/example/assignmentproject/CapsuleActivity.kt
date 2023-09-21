package com.example.assignmentproject

import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.assignmentproject.databinding.ActivityCapsuleBinding
import com.example.assignmentproject.notes.presentation.NotesFragment
import com.example.assignmentproject.quiz.presentation.QuizFragment
import com.example.assignmentproject.utils.extension.hide
import com.example.assignmentproject.utils.extension.show
import com.example.assignmentproject.video.VideoFragment
import com.google.android.material.tabs.TabLayout


class CapsuleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCapsuleBinding
    val confirmDialog by lazy {
        AlertDialog.Builder(this)
            .setTitle("Alert !")
            .setMessage("Your session data will be lost")
            .setCancelable(true)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapsuleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbar()
        setUpViewPager()
        setUpTabLayout()
    }

    private fun setUpToolbar() {
        binding.capsuleToolbar.apply {
            tvToolbarTitle.text = getString(R.string.capsule)
            ivBackButton.setOnClickListener {
                showConfirmDialog()
            }
        }
    }

    private fun showConfirmDialog() {
        confirmDialog
            .setPositiveButton("Exit") { p0, p1 ->
                finish()
            }
            .setNegativeButton("Cancel") { p0, p1 ->
                p0.dismiss()
            }.show()
    }

    private fun setUpTabLayout() {
        binding.tabs.apply {
            setupWithViewPager(binding.viewPager)
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // hide tab layout for last tab (Quiz)
                    isVisible = selectedTabPosition != tabCount - 1
                }
                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun setUpViewPager() {
        val fragments = listOf(
            VideoFragment.newInstance(),
            NotesFragment.newInstance(),
            QuizFragment.newInstance()
        )
        val capsulePagerAdapter = CapsulePagerAdapter(
            this,
            fragments,
            supportFragmentManager
        )
        binding.viewPager.apply {
            adapter = capsulePagerAdapter
        }
    }

    fun changeTab(index : Int){
        binding.tabs.getTabAt(index)?.select()
    }

}