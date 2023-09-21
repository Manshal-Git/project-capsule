package com.example.assignmentproject.quiz

import android.content.Context

class QuizRepoImpl(private val context : Context) : QuizRepository {

    override suspend fun getQuizForChapter(): List<Quiz> {
        TODO("Not yet implemented")
    }

}