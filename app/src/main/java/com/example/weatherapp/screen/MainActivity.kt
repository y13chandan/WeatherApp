package com.example.weatherapp.screen

import android.location.LocationManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.weatherapp.Constants
import com.example.weatherapp.R
import com.example.weatherapp.model.Failed
import com.example.weatherapp.model.Success
import com.example.weatherapp.model.WeatherResponse
import com.example.weatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_screen.*
import kotlinx.android.synthetic.main.weather_details.*
import kotlin.math.roundToInt


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDetails()
    }


    private fun initDetails() {
        callWeatherApi()
        showProgressBar()
        observeApiData()
        handleRetryButton()
    }


    private fun handleRetryButton() {
        btn_retry.setOnClickListener {
            callWeatherApi()
        }
    }

    private fun callWeatherApi() {
        weatherViewModel.getWeatherData(Constants.API_ACCESS_KEY, "NEW YORK")
    }

    private fun showProgressBar() {
        weatherViewModel.progress.observe(this, Observer { showing ->
            if (showing) {
                progress_bar.visibility = View.VISIBLE
            } else {
                progress_bar.visibility = View.GONE
            }
        })
    }

    private fun observeApiData() {
        weatherViewModel.weatherData.observe(this, Observer { response ->
            if (response is Success) {
                if (response.data is WeatherResponse) {
                    weather_view.visibility = View.VISIBLE
                    error_view.visibility = View.GONE
                    val currentWeatherDetail = response.data.current
                    location.text = response.data.location?.name
                    temperature.text = "${currentWeatherDetail?.temperature?.roundToInt()}" + " \u2103"
                    tv_wind_speed.text = currentWeatherDetail?.wind_speed.toString()
                    tv_pressure.text = currentWeatherDetail?.pressure.toString()
                    tv_precip.text = currentWeatherDetail?.precip.toString()
                    tv_cloud_cover.text = currentWeatherDetail?.cloudcover.toString()
                }

            } else if (response is Failed) {
                Toast.makeText(this, response.error, Toast.LENGTH_LONG).show()
                weather_view.visibility = View.GONE
                error_view.visibility = View.VISIBLE
            }
        })
    }
}