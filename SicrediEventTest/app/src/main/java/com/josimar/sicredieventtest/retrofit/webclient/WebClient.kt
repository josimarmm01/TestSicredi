package com.josimar.sicredieventtest.retrofit.webclient

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val UNSUCCESSFUL_REQUEST = "Requisição não sucedida"

abstract class WebClient {

    protected fun <T> execute(call: Call<T>,
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

}