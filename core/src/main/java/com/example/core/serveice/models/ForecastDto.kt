package com.example.core.serveice.models


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ForecastDto(
    val current: Current? = null,
    val location: Location? = null,
    val forecast: Forecast? = null
) : Parcelable

@Parcelize
data class HourItem(
    val feelslikeC: Float? = null,
    val feelslikeF: Float? = null,
    val windDegree: Int? = null,
    val windchillF: Float? = null,
    val windchillC: Float? = null,
    val tempC: Float? = null,
    val tempF: Float? = null,
    val cloud: Int? = null,
    val windKph: Float? = null,
    val windMph: Float? = null,
    val snowCm: Float? = null,
    val humidity: Int? = null,
    val dewpointF: Float? = null,
    val willItRain: Int? = null,
    val uv: Int? = null,
    val heatindexF: Float? = null,
    val dewpointC: Float? = null,
    val isDay: Int? = null,
    val precipIn: Float? = null,
    val heatindexC: Float? = null,
    val windDir: String? = null,
    val gustMph: Float? = null,
    val pressureIn: Float? = null,
    val chanceOfRain: Int? = null,
    val gustKph: Float? = null,
    val precipMm: Float? = null,
    val condition: Condition? = null,
    val willItSnow: Int? = null,
    val visKm: Float? = null,
    val timeEpoch: Int? = null,
    val time: String? = null,
    val chanceOfSnow: Int? = null,
    val pressureMb: Float? = null,
    val visMiles: Float? = null
) : Parcelable

@Parcelize
data class Astro(
    val moonset: String? = null,
    val moonIllumination: Int? = null,
    val sunrise: String? = null,
    val moonPhase: String? = null,
    val sunset: String? = null,
    val isMoonUp: Int? = null,
    val isSunUp: Int? = null,
    val moonrise: String? = null
) : Parcelable

@Parcelize
data class Day(
    val avgvisKm: Float? = null,
    val uv: Float? = null,
    val avgtempF: Float? = null,
    val avgtempC: Float? = null,
    val dailyChanceOfSnow: Int? = null,
    val maxtempC: Float? = null,
    val maxtempF: Float? = null,
    val mintempC: Float? = null,
    val avgvisMiles: Float? = null,
    val dailyWillItRain: Int? = null,
    val mintempF: Float? = null,
    val totalprecipIn: Float? = null,
    val totalsnowCm: Float? = null,
    val avghumidity: Int? = null,
    val condition: Condition? = null,
    val maxwindKph: Float? = null,
    val maxwindMph: Float? = null,
    val dailyChanceOfRain: Int? = null,
    val totalprecipMm: Float? = null,
    val dailyWillItSnow: Int? = null
) : Parcelable

@Parcelize
data class Forecast(
    val forecastday: List<ForecastdayItem?>? = null
) : Parcelable

@Parcelize
data class ForecastdayItem(
    val date: String? = null,
    val astro: Astro? = null,
    val dateEpoch: Int? = null,
    val hour: List<HourItem?>? = null,
    val day: Day? = null
) : Parcelable
