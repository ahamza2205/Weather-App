package com.example.core.utils

import Forecast
import ForecastDay
import ForecastDto
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

fun ForecastDto.toDomain(): Forecast {
    val forecastDays = this.forecast?.forecastday?.map { forecastDayItem ->
        ForecastDay(
            date = forecastDayItem.date ?: "",
            conditionText = forecastDayItem.day?.condition?.text ?: "",
            conditionIcon = forecastDayItem.day?.condition?.icon ?: "",
            minTemp = forecastDayItem.day?.mintemp_c ?: 0f,
            maxTemp = forecastDayItem.day?.maxtemp_c ?: 0f
        )
    } ?: emptyList()
    return Forecast(forecastDays = forecastDays)
}