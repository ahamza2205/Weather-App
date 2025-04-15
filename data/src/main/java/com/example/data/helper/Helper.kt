package com.example.data.helper

import com.example.core.serveice.models.CurrentWeather
import com.example.core.serveice.models.CurrentWeatherDto

fun CurrentWeatherDto.toDomain(): CurrentWeather {
    return CurrentWeather(
        country = location?.country ?: "",
        city = location?.name ?: "",
        temperature = current?.temp_c?.toInt() ?: 0,
        condition = current?.condition?.text ?: "",
        iconUrl = current?.condition?.icon ?: "",
        localTime = location?.localtime ?: "",
        humidity = current?.humidity ?: 0,
        wind_kph = current?.wind_kph?.toInt() ?: 0
    )
}


