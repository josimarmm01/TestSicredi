package com.josimar.sicredieventtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josimar.sicredieventtest.model.Event
import com.josimar.sicredieventtest.repository.EventRepository

class DetailEventViewModel(private val repository: EventRepository) : ViewModel() {

    val isFail = MutableLiveData<Boolean>(false)
    val isLoading = MutableLiveData<Boolean>(true)

    val eventDetail = MutableLiveData<Event?>()

    fun getDetailEvent(eventId: String) : LiveData<EventRepository.Result<Event?>> {
        return  repository.getEventId(eventId = eventId)
    }

}