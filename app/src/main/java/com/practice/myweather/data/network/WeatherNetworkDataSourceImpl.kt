package com.practice.myweather.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.practice.myweather.data.network.response.CurrentWeatherResponse
import com.practice.myweather.internal.NoConnectivityException

private const val TAG = "WeatherNetworkDataSourc"
class WeatherNetworkDataSourceImpl(val weatherStackApiService: WeatherStackApiService) : WeatherNetworkDataSource {
    private var _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchedCurrentWeather = weatherStackApiService.getCurrentWeather(location).await()
            _downloadedCurrentWeather.postValue(fetchedCurrentWeather)
        } catch (e: NoConnectivityException) {
            Log.e(TAG, "No Internet Connection ", e)
        }
    }
}