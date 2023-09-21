package com.example.capsule.notes.domain

interface NotesRepository {
    suspend fun getNote(id : Int) : Note
    suspend fun getNotes() : List<Note>
}