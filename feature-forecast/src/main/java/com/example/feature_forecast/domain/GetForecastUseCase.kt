package com.example.feature_forecast.domain

import Forecast
import com.example.data.repository.WeatherRepository
import javax.inject.Inject
import com.example.core.utils.toDomain

class GetForecastUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): Forecast {
        val response = repository.getForecast(city)
        return if (response.isSuccessful) {
            response.body()?.toDomain() ?: throw Exception("Forecast data not found")
        } else {
            throw Exception("Failed to fetch forecast")
        }
    }
}