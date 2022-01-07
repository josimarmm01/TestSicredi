package com.josimar.sicredieventtest.model

data class Event(val people: ArrayList<String>? = null,
                 val date: Long? = null,
                 val description: String? = null,
                 val image: String? = null,
                 val longitude: Double? = null,
                 val latitude: Double? = null,
                 val price: Double? = null,
                 val title: String? = null,
                 val id: String? = null)