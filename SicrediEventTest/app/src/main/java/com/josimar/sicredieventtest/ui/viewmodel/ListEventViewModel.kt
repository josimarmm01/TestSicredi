package com.josimar.sicredieventtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.josimar.sicredieventtest.model.Event
import com.josimar.sicredieventtest.repository.EventRepository
import com.josimar.sicredieventtest.repository.Resource

class ListEventViewModel(private val repository: EventRepository) : ViewModel() {

    val isFail = MutableLiveData<Boolean>(false)
    val isEmpty = MutableLiveData<Boolean>(false)
    val isLoading = MutableLiveData<Boolean>(true)

    val listEvent = MutableLiveData<List<Event>?>()
    val errorList = MutableLiveData<String?>()

    init {
        observerResorceListEvent()
    }

    fun getListEvent() : LiveData<Resource<List<Event>?>> {
        return repository.getListEvent()
    }

    private fun observerResorceListEvent() {

        listEvent.observeForever {
            isEmptyListEvent(it)
        }

        errorList.observeForever {
            isErrorListEvent(it)
        }

    }

    private fun isEmptyListEvent(data: List<Event>?) {
        isLoading.value = false
        data?.let { isEmpty.value = data.isEmpty() }
    }

    private fun isErrorListEvent(error: String?) {
        isLoading.value = false
        isFail.value = true
    }

}