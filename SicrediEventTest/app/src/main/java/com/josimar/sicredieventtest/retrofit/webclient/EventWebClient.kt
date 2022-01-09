package com.josimar.sicredieventtest.retrofit.webclient

import com.josimar.sicredieventtest.model.CheckIn
import com.josimar.sicredieventtest.model.Event
import com.josimar.sicredieventtest.model.ResponeCheckIn
import com.josimar.sicredieventtest.retrofit.AppRetrofit
import com.josimar.sicredieventtest.retrofit.service.EventService

class EventWebClient(private val service: EventService = AppRetrofit().eventService) : WebClient() {

    fun getListEvent(success: (listEvent: List<Event>?) -> Unit,
                     failure: (error: String?) -> Unit) {
        execute(service.getListEvent(), success, failure)
    }

    fun getEventId(eventId: String,
                   success: (event: Event?) -> Unit,
                   failure: (error: String?) -> Unit) {
        execute(service.getEventId(eventId = eventId), success, failure)
    }

    fun setCheckIn(checkIn: CheckIn,
                   success: (event: ResponeCheckIn?) -> Unit,
                   failure: (error: String?) -> Unit) {
        execute(service.setCheckIn(checkIn = checkIn), success, failure)
    }

}