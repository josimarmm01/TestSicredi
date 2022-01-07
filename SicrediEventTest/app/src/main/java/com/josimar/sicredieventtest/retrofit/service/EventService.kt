package com.josimar.sicredieventtest.retrofit.service

import com.josimar.sicredieventtest.model.CheckIn
import com.josimar.sicredieventtest.model.Event
import com.josimar.sicredieventtest.model.ResponeCheckIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventService {

    @GET("events")
    fun getListEvent(): Call<List<Event>>

    @GET("events/{eventId}")
    fun getEventId(@Path("eventId") eventId: String): Call<Event>

    @POST("/checkin")
    fun setCheckIn(@Body checkIn: CheckIn): Call<ResponeCheckIn>
}