package com.example.capsule.quiz.domain

data class Quiz(
    val id : Int,
    val question : String,
    val options : List<String>,
    val correctAnswer : String
)
