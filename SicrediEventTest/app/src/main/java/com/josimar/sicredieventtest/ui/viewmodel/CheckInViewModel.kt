package com.josimar.sicredieventtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josimar.sicredieventtest.model.CheckIn
import com.josimar.sicredieventtest.model.ResponeCheckIn
import com.josimar.sicredieventtest.repository.EventRepository
import com.josimar.sicredieventtest.repository.Resource

class CheckInViewModel(private val repository: EventRepository) : ViewModel() {

    val isSuccess = MutableLiveData<Boolean>(false)
    
    fun setCheckIn(checkIn: CheckIn) : LiveData<Resource<ResponeCheckIn>?> {
        return  repository.setCheckIn(checkIn = checkIn)
    }
}