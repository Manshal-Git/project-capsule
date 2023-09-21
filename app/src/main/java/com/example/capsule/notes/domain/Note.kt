package com.example.capsule.notes.domain

data class Note(
    val title : String,
    val content : String,
    var images : List<String> = emptyList()
)
