package com.example.core.serveice

import com.example.core.serveice.models.CurrentWeatherDto
import com.example.core.serveice.models.ForecastDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String,
        @Query("q") city: String
    ): Response<CurrentWeatherDto>

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") days: Int = 5
    ): Response<ForecastDto>
}