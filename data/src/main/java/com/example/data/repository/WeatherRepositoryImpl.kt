package com.example.data.repository

import com.example.core.serveice.WeatherApiService
import com.example.core.serveice.models.CurrentWeather
import com.example.core.serveice.models.CurrentWeatherDto
import retrofit2.Response
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService
) : WeatherRepository {

    private val apiKey = "c4fd5335d6fa4b86802225847251404"

    override suspend fun getCurrentWeather(city: String): Response<CurrentWeatherDto> {
        return api.getCurrentWeather(apiKey, city)
    }
}
