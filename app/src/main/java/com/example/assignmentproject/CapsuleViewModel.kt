package com.example.assignmentproject

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CapsuleViewModel : ViewModel() {

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