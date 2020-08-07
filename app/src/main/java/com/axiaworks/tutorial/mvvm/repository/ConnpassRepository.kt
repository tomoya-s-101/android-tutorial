package com.axiaworks.tutorial.mvvm.repository

import com.axiaworks.tutorial.mvvm.response.ConnpassEvents
import retrofit2.http.GET
import retrofit2.http.Query

interface ConnpassRepository {

    @GET("api/v1/event/")
    suspend fun fetchTagEvents(@Query("keyword") keyword: String): ConnpassEvents
}