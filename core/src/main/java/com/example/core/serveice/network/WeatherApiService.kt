package com.example.core.serveice.network

import ForecastDto
import com.example.core.BuildConfig
import com.example.core.serveice.models.CurrentWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") city: String
    ): Response<CurrentWeatherDto>

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") city: String,
        @Query("days") days: Int = 6
    ): Response<ForecastDto>
}