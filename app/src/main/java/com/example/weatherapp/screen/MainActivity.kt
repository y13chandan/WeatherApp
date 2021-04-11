package com.example.weatherapp.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.weatherapp.R
import com.example.weatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.weatherapp.Constants

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weatherViewModel.getWeatherData(Constants.API_ACCESS_KEY, "NEW YORK")
        weatherViewModel.weatherData.observe(this, Observer { response->
            if (response != null) {
                Log.d("api success", response.toString())
            }
        })
    }
}