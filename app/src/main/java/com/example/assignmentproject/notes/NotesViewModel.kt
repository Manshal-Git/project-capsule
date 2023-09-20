package com.example.assignmentproject.notes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.assignmentproject.AppModule
import com.example.assignmentproject.data.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
   val notesRepository : NotesRepository
) : ViewModel() {

    private val _notesUIState = MutableLiveData<Response<List<Note>>>()
    val notesUIState: LiveData<Response<List<Note>>> get() = _notesUIState
    fun setNotesUIState(value : Response<List<Note>>){
        _notesUIState.postValue(value)
    }


    fun getNotes() {

    }

    fun getNote(id : Int){
        val note = notesRepository.getNote(id)
        setNotesUIState(Response.Success(listOf(note)))
    }

}