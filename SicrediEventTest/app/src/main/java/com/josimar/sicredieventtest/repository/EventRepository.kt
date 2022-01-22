package com.josimar.sicredieventtest.repository

import androidx.lifecycle.liveData
import com.josimar.sicredieventtest.model.CheckIn
import com.josimar.sicredieventtest.retrofit.AppRetrofit
import com.josimar.sicredieventtest.retrofit.service.EventService
import java.net.ConnectException

private const val MSG_FAIL = "Falha ao buscar o endereco"

class EventRepository(private val service: EventService = AppRetrofit().eventService) {

    sealed class Result<out R> {
        data class Success<out T>(val data: T?) : Result<T?>()
        data class Error(val exception: Exception) : Result<Nothing>()
    }

    fun getListEvent() = liveData {

        try {
            val response = service.getListEvent()
            if (response.isSuccessful)
                emit(Result.Success(data = response.body()))
            else
                emit(Result.Error(exception = Exception(MSG_FAIL)))

        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun getEventId(eventId: String) = liveData {

        try {
            val response = service.getEventId(eventId = eventId)
            if (response.isSuccessful)
                emit(Result.Success(data = response.body()))
            else
                emit(Result.Error(exception = Exception(MSG_FAIL)))

        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

    fun setCheckIn(checkIn: CheckIn) = liveData {

        try {
            val response = service.setCheckIn(checkIn = checkIn)
            if (response.isSuccessful)
                emit(Result.Success(data = response.body()))
            else
                emit(Result.Error(exception = Exception(MSG_FAIL)))

        } catch (e: Exception) {
            emit(Result.Error(exception = e))
        }
    }

}