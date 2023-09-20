package com.example.assignmentproject.data

sealed class Response<T> {
    class Loading<T>(val progress: Double) : Response<T>()
    class Success<T>(val data: T) : Response<T>()
    class Error<T>(val error : String,val e : Exception? = null) : Response<T>()
}
