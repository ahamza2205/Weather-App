package com.example.data.repository

import com.example.core.serveice.models.CurrentWeatherDto
import com.example.core.serveice.models.ForecastDto
import retrofit2.Response

interface WeatherRepository {
    suspend fun getCurrentWeather(city: String): Response<CurrentWeatherDto>
}
