package com.example.weatherapp.repository

import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.network.ApiService
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getWeatherData(accessKey: String, cityName: String): WeatherResponse = apiService.getWeatherData(accessKey, cityName)
}