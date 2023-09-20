package com.example.assignmentproject.notes

data class Note(
    val title : String,
    val content : String,
    var images : List<String> = emptyList()
)
