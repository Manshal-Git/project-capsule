package com.example.capsule.quiz.domain

interface QuizRepository {

    suspend fun getQuizForChapter() : List<Quiz>

}