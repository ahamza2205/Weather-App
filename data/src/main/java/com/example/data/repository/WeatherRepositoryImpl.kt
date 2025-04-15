package com.example.data.repository

import com.example.data.remot.WeatherApiService
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService
) : WeatherRepository {
    private val apiKey = "YOUR_API_KEY"

    override suspend fun getCurrentWeather(city: String) =
        api.getCurrentWeather(apiKey, city)

    override suspend fun getForecast(city: String) =
        api.getForecast(apiKey, city)
}
