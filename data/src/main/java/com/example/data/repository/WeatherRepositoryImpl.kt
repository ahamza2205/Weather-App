package com.example.data.repository

import ForecastDto
import com.example.core.serveice.WeatherApiService
import com.example.core.serveice.models.CurrentWeatherDto
import retrofit2.Response
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApiService
) : WeatherRepository {

    override suspend fun getCurrentWeather(city: String): Response<CurrentWeatherDto> {
        return api.getCurrentWeather(city = city)
    }

    override suspend fun getForecast(city: String): Response<ForecastDto> {
        return api.getForecast(city = city)
    }
}

