package com.example.assignmentproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.assignmentproject.databinding.ActivityCapsuleBinding
import com.example.assignmentproject.notes.NotesFragment
import com.example.assignmentproject.video.VideoFragment
import com.google.android.material.tabs.TabLayout

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CapsuleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCapsuleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapsuleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewPager()
        setUpTabLayout()
    }

    private fun setUpTabLayout() {
        binding.tabs.apply {
            setupWithViewPager(binding.viewPager)
        }
    }

    private fun setUpViewPager() {
        val fragments = listOf(
            VideoFragment.newInstance(),
            NotesFragment.newInstance()
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