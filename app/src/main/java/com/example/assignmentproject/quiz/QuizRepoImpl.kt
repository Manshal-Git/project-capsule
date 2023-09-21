package com.example.assignmentproject.quiz

import android.content.Context
import kotlinx.coroutines.delay

class QuizRepoImpl(private val context : Context) : QuizRepository {

    override suspend fun getQuizForChapter(): List<Quiz> {
        delay(2000)
        return  listOf(
            Quiz(
                id = 1,
                question = "What is the capital of France?",
                options = listOf("London", "Madrid", "Paris", "Rome"),
                correctAnswer = "Paris"
            ),
            Quiz(
                id = 2,
                question = "Which planet is known as the 'Red Planet'?",
                options = listOf("Earth", "Mars", "Venus", "Jupiter"),
                correctAnswer = "Mars"
            ),
            Quiz(
                id = 3,
                question = "Who wrote the play 'Romeo and Juliet'?",
                options = listOf("Charles Dickens", "William Shakespeare", "Jane Austen", "Leo Tolstoy"),
                correctAnswer = "William Shakespeare"
            ),
            Quiz(
                id = 4,
                question = "Which gas do plants absorb from the atmosphere?",
                options = listOf("Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"),
                correctAnswer = "Carbon Dioxide"
            ),
            Quiz(
                id = 5,
                question = "What is the largest mammal in the world?",
                options = listOf("African Elephant", "Giraffe", "Blue Whale", "Hippopotamus"),
                correctAnswer = "Blue Whale"
            )
        )
    }

}