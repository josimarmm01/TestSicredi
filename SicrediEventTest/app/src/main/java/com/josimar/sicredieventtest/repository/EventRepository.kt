package com.josimar.sicredieventtest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.josimar.sicredieventtest.model.CheckIn
import com.josimar.sicredieventtest.model.Event
import com.josimar.sicredieventtest.model.ResponeCheckIn
import com.josimar.sicredieventtest.retrofit.webclient.EventWebClient

class EventRepository(private val webclient: EventWebClient = EventWebClient()) {

    fun getListEvent(): LiveData<Resource<List<Event>?>> {

        val lisEvent = MutableLiveData<Resource<List<Event>?>>()

        webclient.getListEvent(success = {
            lisEvent.value = Resource(it)
        }, failure = {
            lisEvent.value = Resource(null, it)
        })

        return lisEvent
    }

    fun getEventId(eventId: String): LiveData<Resource<Event>?> {

        val event = MutableLiveData<Resource<Event>?>()

        webclient.getEventId(eventId, success = {
            event.value = Resource(it)
        }, failure = {
            event.value = Resource(null, it)
        })

        return event
    }

    fun setCheckIn(checkIn: CheckIn): LiveData<Resource<ResponeCheckIn>?> {

        val event = MutableLiveData<Resource<ResponeCheckIn>?>()

        webclient.setCheckIn(checkIn = checkIn, success = {
            event.value = Resource(it)
        }, failure = {
            event.value = Resource(null, it)
        })

        return event
    }

}