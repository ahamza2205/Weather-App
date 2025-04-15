package com.example.feature_currentweather.presentation.domain


import com.example.data.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
     //   return repository.getCurrentWeather(city).toDomain()

}
