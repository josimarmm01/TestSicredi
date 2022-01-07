package com.josimar.sicredieventtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josimar.sicredieventtest.model.Event
import com.josimar.sicredieventtest.repository.EventRepository
import com.josimar.sicredieventtest.repository.Resource

class ListEventViewModel(repository: EventRepository) : ViewModel() {

    val isFail = MutableLiveData<Boolean>(false)
    val isEmpty = MutableLiveData<Boolean>(false)
    val isLoading = MutableLiveData<Boolean>(true)

    val getListEvent: LiveData<Resource<List<Event>?>> = repository.getListEvent()

}