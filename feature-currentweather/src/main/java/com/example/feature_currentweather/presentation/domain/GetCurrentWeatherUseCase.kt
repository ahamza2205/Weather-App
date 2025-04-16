package com.example.feature_currentweather.presentation.domain


import com.example.core.serveice.models.CurrentWeather
import com.example.data.repository.WeatherRepository
import com.example.core.utils.toDomain
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(city: String): CurrentWeather {
        val response = repository.getCurrentWeather(city)
        return response.body()?.toDomain()
            ?: throw Exception("Weather data not found")
    }
}

