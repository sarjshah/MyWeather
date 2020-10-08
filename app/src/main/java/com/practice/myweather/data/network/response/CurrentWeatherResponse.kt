package com.practice.myweather.data.network.response

import com.google.gson.annotations.SerializedName
import com.practice.myweather.data.db.entity.CurrentWeatherEntry
import com.practice.myweather.data.db.entity.Location

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntry: CurrentWeatherEntry,
    @SerializedName("location")
    val location: Location
)