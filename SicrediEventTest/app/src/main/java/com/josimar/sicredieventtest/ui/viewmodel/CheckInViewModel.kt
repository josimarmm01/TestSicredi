package com.josimar.sicredieventtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josimar.sicredieventtest.model.CheckIn
import com.josimar.sicredieventtest.model.ResponeCheckIn
import com.josimar.sicredieventtest.repository.EventRepository

class CheckInViewModel(private val repository: EventRepository) : ViewModel() {

    val isSuccess = MutableLiveData<Boolean>(false)
    
    fun setCheckIn(checkIn: CheckIn) : LiveData<EventRepository.Result<ResponeCheckIn?>> {
        return  repository.setCheckIn(checkIn = checkIn)
    }
}