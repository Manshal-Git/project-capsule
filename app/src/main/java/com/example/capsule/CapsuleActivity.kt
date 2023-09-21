package com.example.capsule

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.capsule.databinding.ActivityCapsuleBinding
import com.example.capsule.notes.presentation.NotesFragment
import com.example.capsule.quiz.presentation.QuizFragment
import com.example.capsule.video.VideoFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.time.Duration.Companion.minutes


class CapsuleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCapsuleBinding

    private val confirmDialog by lazy {
        AlertDialog.Builder(this)
    }

    private val viewModel by viewModels<CapsuleViewModel>()

    val fragments = listOf(
        VideoFragment.newInstance(),
        NotesFragment.newInstance(),
        QuizFragment.newInstance()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapsuleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolbar()
        setUpViewPager()
        setUpTabLayout()
        startSessionTimer()
        viewModel.isLastTab.observe(this) { isLastTab ->
            binding.apply {
                tabs.isVisible = !isLastTab
                changeAppColorSheme(isLastTab)
            }
        }
    }

    private fun changeAppColorSheme(lastTab: Boolean) {
        val toolbarColor =
            if (lastTab) R.color.smooth_blue
            else R.color.white

        binding.root.backgroundTintList =
            AppCompatResources.getColorStateList(
                this@CapsuleActivity,
                toolbarColor
            )

        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = resources.getColor(toolbarColor)
    }

    private fun startSessionTimer() {
        lifecycleScope.launch {
            viewModel.setTimerFlow(2.minutes.inWholeSeconds)
                .flowOn(Dispatchers.IO)
                .onEach { setUpTimerView(it) }
                .onCompletion {  showTimeOverDialog() }
                .collect()
        }
    }

    private fun setUpTimerView(seconds: Long) {
        val minutes = seconds / 60
        binding.capsuleToolbar.tvSessionTimer.text =
            "$minutes:${if (seconds % 60 > 9) seconds % 60 else "0${seconds % 60}"}"
    }

    private fun setUpToolbar() {
        binding.capsuleToolbar.apply {
            tvToolbarTitle.text = getString(R.string.capsule)
            ivBackButton.setOnClickListener {
                showExitConfirmDialog()
            }
        }
    }

    private fun showExitConfirmDialog() {
        confirmDialog
            .setTitle("Alert !")
            .setMessage("Your session data will be lost")
            .setCancelable(true)
            .setPositiveButton("Exit") { p0, p1 ->
                finish()
            }
            .setNegativeButton("Cancel") { p0, p1 ->
                p0.dismiss()
            }.show()
    }

    private fun showTimeOverDialog() {
        // can cause a crash due to called through flow's coroutine
        try {
            AlertDialog.Builder(this)
                .setTitle("Time Over !")
                .setMessage("Don't worry, You can come back again !")
                .setCancelable(false)
                .setPositiveButton("Exit") { p0, p1 ->
                    finish()
                }
                .show()
        }catch (e : Exception){
            Log.e("CapsuleActivity",e.toString())
        }
    }

    private fun setUpTabLayout() {
        binding.tabs.apply {
            setupWithViewPager(binding.viewPager)
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    // hide tab layout for last tab (Quiz)
                    viewModel.setIsLastTab(selectedTabPosition == tabCount - 1)
                    // pausing first fragment(VideoFragment) to handle viewView behaviour
                    if(selectedTabPosition !=  0)
                        fragments.first().onPause()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {}
                override fun onTabReselected(tab: TabLayout.Tab?) {}
            })
        }
    }

    private fun setUpViewPager() {
        val capsulePagerAdapter = CapsulePagerAdapter(
            this,
            fragments,
            supportFragmentManager
        )
        binding.viewPager.apply {
            adapter = capsulePagerAdapter
        }
    }

    fun changeTab(index: Int) {
        binding.tabs.getTabAt(index)?.select()
    }

    override fun onBackPressed() {
        showExitConfirmDialog()
    }

}