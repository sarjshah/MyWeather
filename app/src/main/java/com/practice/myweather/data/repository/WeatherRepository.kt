package com.practice.myweather.data.repository

import androidx.lifecycle.LiveData
import com.practice.myweather.data.db.entity.CurrentWeatherEntry

interface WeatherRepository {
    suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntry>
}