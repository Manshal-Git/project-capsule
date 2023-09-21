package com.example.capsule.notes.presentation

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.capsule.notes.data.NotesRepositoryImpl

class NotesViewModelFactory(private val context: Activity) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotesViewModel(NotesRepositoryImpl(context)) as T
    }
}