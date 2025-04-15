package com.example.data.mapper

import com.example.data.remot.CurrentWeatherDto
import com.example.feature_currentweather.presentation.domain.CurrentWeather

fun CurrentWeatherDto.toDomain(): CurrentWeather? {
    return this.current?.condition?.text?.let {
        CurrentWeather(
        temperature = this.current?.tempC,
        condition = it
    )
    }
}
