package com.example.capsule

import com.example.capsule.notes.data.NotesRepositoryImpl
import com.example.capsule.notes.presentation.NotesViewModel
import com.example.capsule.quiz.data.QuizRepoImpl
import com.example.capsule.quiz.presentation.QuizViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single {
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