package com.example.data.remot

import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {
    @GET("current.json")
    suspend fun getCurrentWeather(
        @Query("key") key: String,
        @Query("q") city: String
    ): CurrentWeatherDto

    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") key: String,
        @Query("q") city: String,
        @Query("days") days: Int = 5
    ): ForecastDto
}
