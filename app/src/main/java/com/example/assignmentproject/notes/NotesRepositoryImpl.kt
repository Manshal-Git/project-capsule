package com.example.assignmentproject.notes

import android.content.Context
import dagger.Binds
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class NotesRepositoryImpl @Inject constructor(
    @ApplicationContext val context: Context
) : NotesRepository {
    override fun getNote(id: Int): Note {
        return Note(
            "NCERT NOTES",
            context.getString(id)
        )
    }

    override fun getNotes(): List<Note> {
        return emptyList()
    }

}