package com.example.capsule.data

sealed class Response<T>(var process: Double? = null, var msg : String? = null, var data : T? = null) {
    class Loading<T>(progress: Double) : Response<T>(process = progress)
    class Success<T>(data: T) : Response<T>(data = data)
    class Error<T>(error : String,val exception : Exception? = null) : Response<T>(msg = error)
}
