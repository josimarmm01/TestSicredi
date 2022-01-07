package com.josimar.sicredieventtest.retrofit.webclient

import com.josimar.sicredieventtest.model.CheckIn
import com.josimar.sicredieventtest.model.Event
import com.josimar.sicredieventtest.model.ResponeCheckIn
import com.josimar.sicredieventtest.retrofit.AppRetrofit
import com.josimar.sicredieventtest.retrofit.service.EventService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val UNSUCCESSFUL_REQUEST = "Requisição não sucedida"

class EventWebClient(private val service: EventService = AppRetrofit().eventService) {

    private fun <T> execute(call: Call<T>,
                            success: (success: T?) -> Unit,
                            failure: (error: String?) -> Unit) {
        call.enqueue(object : Callback<T> {

            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    success(response.body())
                } else {
                    failure(UNSUCCESSFUL_REQUEST)
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                failure(t.message)
            }
        })
    }

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