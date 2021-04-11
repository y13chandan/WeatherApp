package com.example.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {
    val weatherData: MutableLiveData<WeatherResponse> = MutableLiveData()

    fun getWeatherData(accessKey: String, cityName: String) {
        viewModelScope.launch {
            val response = weatherRepository.getWeatherData(accessKey, cityName)
            weatherData.value = response
        }
    }
}