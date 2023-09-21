package com.example.capsule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CapsuleViewModel : ViewModel() {

    private val _isLastTab = MutableLiveData<Boolean>()
    val isLastTab: LiveData<Boolean> get() = _isLastTab
    fun setIsLastTab(value : Boolean){
        _isLastTab.postValue(value)
    }
    fun setTimerFlow (initialValue: Long): Flow<Long> {
        return flow {
            var currentValue = initialValue
            while (currentValue >= 0) {
                emit(currentValue)
                delay(1000)
                currentValue--
            }
        }
    }
}