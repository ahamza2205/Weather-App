package com.example.data.repository

import ForecastDto
import com.example.core.serveice.WeatherApiService
import com.example.core.serveice.models.CurrentWeatherDto
import com.example.data.BuildConfig
import retrofit2.Response
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService
) : WeatherRepository {

    private val apiKey = BuildConfig.API_KEY


    override suspend fun getCurrentWeather(city: String): Response<CurrentWeatherDto> {
        return api.getCurrentWeather(apiKey, city)
    }

    override suspend fun getForecast(city: String): Response<ForecastDto> {
        return api.getForecast(apiKey, city)
    }
}
