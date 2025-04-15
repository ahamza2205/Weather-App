package com.example.data.repository

import com.example.data.remot.CurrentWeatherDto
import com.example.data.remot.ForecastDto


interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): CurrentWeatherDto
    suspend fun getForecast(city: String): ForecastDto
}
