package com.example.assignmentproject.notes.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignmentproject.data.Response
import com.example.assignmentproject.notes.domain.Note
import com.example.assignmentproject.notes.domain.NotesRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NotesViewModel (
    private val notesRepository : NotesRepository
) : ViewModel() {


    private val _notesUIState = MutableLiveData<Response<List<Note>>>()
    val notesUIState: LiveData<Response<List<Note>>> get() = _notesUIState
    fun setNotesUIState(value : Response<List<Note>>){
        _notesUIState.postValue(value)
    }


    fun getNotes() {

    }

    fun getNote(id : Int){
        viewModelScope.launch {
            val note = notesRepository.getNote(id)
            setNotesUIState(Response.Success(listOf(note)))
        }
    }

}