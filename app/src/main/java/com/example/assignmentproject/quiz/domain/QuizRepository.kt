package com.example.assignmentproject.quiz.domain

import com.example.assignmentproject.quiz.domain.Quiz

interface QuizRepository {

    suspend fun getQuizForChapter() : List<Quiz>

}