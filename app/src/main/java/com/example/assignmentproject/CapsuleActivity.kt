package com.example.assignmentproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.assignmentproject.databinding.ActivityCapsuleBinding

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CapsuleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCapsuleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCapsuleBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}