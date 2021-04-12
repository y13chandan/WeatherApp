package com.example.weatherapp.repository

import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.ApiService
import com.example.weatherapp.network.SafeApiRequest
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService): SafeApiRequest() {
    suspend fun getWeatherData(accessKey: String, cityName: String): WeatherResponse {
        return apiRequest { apiService.getWeatherData(accessKey, cityName) }
    }
}