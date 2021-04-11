package com.example.weatherapp.network

import com.example.weatherapp.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("current/")
    suspend fun getWeatherData(
        @Query("access_key") accessKey:String,
        @Query("query") query:String
    ): WeatherResponse

}