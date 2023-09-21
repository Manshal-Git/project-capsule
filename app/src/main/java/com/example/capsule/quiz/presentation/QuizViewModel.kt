package com.example.capsule.quiz.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capsule.data.Response
import com.example.capsule.quiz.domain.Quiz
import com.example.capsule.quiz.domain.QuizRepository
import kotlinx.coroutines.launch

class QuizViewModel(private val quizRepository: QuizRepository) : ViewModel() {

    private val _questions = MutableLiveData<Response<List<Quiz>>>()
    val questions: LiveData<Response<List<Quiz>>> get() = _questions
    private fun setQuestionsState(value : Response<List<Quiz>>){
        _questions.postValue(value)
    }

    private val _currentQuiz = MutableLiveData<Quiz>()
    val currentQuiz: LiveData<Quiz> get() = _currentQuiz
    private fun setCurrentQuiz(value: Quiz) {
        _currentQuiz.postValue(value)
    }

    var answeredCorrectly = 0

    fun getQuizForChapter(){
        setQuestionsState(Response.Loading(0.0))
        viewModelScope.launch {
            val questions = quizRepository.getQuizForChapter()
            setQuestionsState(Response.Success(questions))
        }
    }

    fun showNextQuiz(){
        val nextQuiz = getNextQuiz() ?: throw ArrayIndexOutOfBoundsException()
        setCurrentQuiz(nextQuiz)
    }

    private fun getNextQuiz(): Quiz?  {
        val nextQuiz = _questions.value?.data!!.let { quizList ->
            val currentQuiz = _currentQuiz.value ?: return@let quizList.first()

            val currentQuizIndex = quizList.indexOf(currentQuiz)
            quizList[currentQuizIndex + 1]

        }
        return nextQuiz
    }

}