package com.example.assignmentproject.data

sealed class Response<T>(var process: Double? = null,var error : String? = null,var data : T? = null) {
    class Loading<T>(progress: Double) : Response<T>(process = progress)
    class Success<T>(data: T) : Response<T>(data = data)
    class Error<T>(error : String,val e : Exception? = null) : Response<T>(error = error)
}
