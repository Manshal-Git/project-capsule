package com.example.assignmentproject.quiz

interface QuizRepository {

    suspend fun getQuizForChapter() : List<Quiz>

}