package com.example.assignmentproject

import com.example.assignmentproject.notes.data.NotesRepositoryImpl
import com.example.assignmentproject.notes.presentation.NotesViewModel
import com.example.assignmentproject.quiz.QuizRepoImpl
import com.example.assignmentproject.quiz.QuizViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    factory {
        QuizRepoImpl(androidContext())
    }

    viewModel {
        QuizViewModel(get<QuizRepoImpl>())
    }

    factory {
        NotesRepositoryImpl(androidContext())
    }
    viewModel {
        NotesViewModel(get<NotesRepositoryImpl>())
    }


}