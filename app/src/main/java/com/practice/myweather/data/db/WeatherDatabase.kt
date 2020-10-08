package com.practice.myweather.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class WeatherDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object {
        @Volatile private var instance: WeatherDatabase ?= null

        private val LOCK = Any()

        operator fun invoke(context: Context): WeatherDatabase = instance ?: synchronized(LOCK) {
            instance?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                WeatherDatabase::class.java,
                "weather.db"
            ).build()
    }

}

