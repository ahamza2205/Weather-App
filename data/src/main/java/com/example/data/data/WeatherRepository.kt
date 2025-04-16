package com.example.data.data

import ForecastDto
import com.example.core.serveice.models.CurrentWeatherDto
import retrofit2.Response

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): Response<CurrentWeatherDto>
    suspend fun getForecast(city: String): Response<ForecastDto>
}