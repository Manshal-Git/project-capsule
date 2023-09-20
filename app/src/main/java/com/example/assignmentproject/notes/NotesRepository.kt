package com.example.assignmentproject.notes

interface NotesRepository {
    fun getNote(id : Int) : Note
    fun getNotes() : List<Note>
}