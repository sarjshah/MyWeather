package com.practice.myweather.ui.weather.Current

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.practice.myweather.R
import com.practice.myweather.data.network.ConnectivityInterceptorImpl
import com.practice.myweather.data.network.WeatherNetworkDataSourceImpl
import com.practice.myweather.data.network.WeatherStackApiService
import kotlinx.android.synthetic.main.current_weather_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CurrentWeatherFragment : Fragment() {

    companion object {
        fun newInstance() =
            CurrentWeatherFragment()
    }

    private lateinit var viewModel: CurrentWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.current_weather_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentWeatherViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onResume() {
        super.onResume()
        val apiService = WeatherStackApiService(ConnectivityInterceptorImpl(this.requireContext()))
         val weatherNetworkDataSource = WeatherNetworkDataSourceImpl(apiService)
        GlobalScope.launch {
            weatherNetworkDataSource.fetchCurrentWeather("london")

        }
        weatherNetworkDataSource.downloadedCurrentWeather.observe(this, Observer {
            tvText.text = it.toString()
        })
    }

}