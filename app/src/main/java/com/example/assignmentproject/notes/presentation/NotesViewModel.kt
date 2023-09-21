package com.example.assignmentproject.notes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentproject.data.Response
import com.example.assignmentproject.notes.domain.Note
import com.example.assignmentproject.notes.domain.NotesRepository
import kotlinx.coroutines.launch

class NotesViewModel (
    private val notesRepository : NotesRepository
) : ViewModel() {


    private val _notes = MutableLiveData<Response<List<Note>>>()
    val notes: LiveData<Response<List<Note>>> get() = _notes
    private fun setNotes(value : Response<List<Note>>){
        _notes.postValue(value)
    }
    fun getNote(id : Int){
        viewModelScope.launch {
            val note = notesRepository.getNote(id)
            setNotes(Response.Success(listOf(note)))
        }
    }

}