package com.josimar.sicredieventtest.retrofit.service

import com.josimar.sicredieventtest.model.CheckIn
import com.josimar.sicredieventtest.model.Event
import com.josimar.sicredieventtest.model.ResponeCheckIn
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventService {

    @GET("events")
    suspend fun getListEvent(): Response<List<Event>>

    @GET("events/{eventId}")
    suspend fun getEventId(@Path("eventId") eventId: String): Response<Event>

    @POST("checkin")
    suspend fun setCheckIn(@Body checkIn: CheckIn): Response<ResponeCheckIn>
}