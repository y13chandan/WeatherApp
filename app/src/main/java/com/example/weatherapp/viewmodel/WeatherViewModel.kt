package com.example.weatherapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.model.Failed
import com.example.weatherapp.model.Success
import com.example.weatherapp.model.UIState
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository): ViewModel() {
    val weatherData: MutableLiveData<UIState> = MutableLiveData()
    val progress = MutableLiveData<Boolean>()

    fun getWeatherData(accessKey: String, cityName: String) {
        progress.value = true
        viewModelScope.launch {
            try {
                val response = weatherRepository.getWeatherData(accessKey, cityName)
                weatherData.value = Success(response)
                Log.d("responsevalue", response.toString())
                progress.value = false
            } catch (e: Exception) {
                e.message?.let {
                    weatherData.value = Failed(it)
                }
                progress.value = false
            }
        }
    }
}