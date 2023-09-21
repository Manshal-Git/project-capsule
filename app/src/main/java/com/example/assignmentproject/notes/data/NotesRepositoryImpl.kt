package com.example.assignmentproject.notes.data

import android.content.Context
import com.example.assignmentproject.notes.domain.Note
import com.example.assignmentproject.notes.domain.NotesRepository
import kotlinx.coroutines.delay


class NotesRepositoryImpl (
    private val context: Context
) : NotesRepository {
    override suspend fun getNote(id: Int): Note {
        delay(2000)
        return Note(
            "NCERT NOTES",
            context.getString(id)
        )
    }

    override suspend fun getNotes(): List<Note> {
        return emptyList()
    }

}