package com.example.data.remot

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

@Parcelize
data class ForecastDto(
	val current: Current? = null,
	val location: Location? = null,
	val forecast: Forecast? = null
) : Parcelable

@Parcelize
data class HourItem(
	val feelslikeC: @RawValue Any? = null,
	val feelslikeF: @RawValue  Any? = null,
	val windDegree: Int? = null,
	val windchillF: @RawValue  Any? = null,
	val windchillC: @RawValue  Any? = null,
	val tempC: @RawValue  Any? = null,
	val tempF: @RawValue  Any? = null,
	val cloud: Int? = null,
	val windKph: @RawValue  Any? = null,
	val windMph: @RawValue  Any? = null,
	val snowCm: @RawValue  Any? = null,
	val humidity: Int? = null,
	val dewpointF: @RawValue  Any? = null,
	val willItRain: Int? = null,
	val uv: Int? = null,
	val heatindexF: @RawValue  Any? = null,
	val dewpointC: @RawValue  Any? = null,
	val isDay: Int? = null,
	val precipIn: @RawValue  Any? = null,
	val heatindexC: @RawValue  Any? = null,
	val windDir: String? = null,
	val gustMph: @RawValue  Any? = null,
	val pressureIn: @RawValue  Any? = null,
	val chanceOfRain: Int? = null,
	val gustKph: @RawValue  Any? = null,
	val precipMm: @RawValue  Any? = null,
	val condition: Condition? = null,
	val willItSnow: Int? = null,
	val visKm: @RawValue  Any? = null,
	val timeEpoch: Int? = null,
	val time: String? = null,
	val chanceOfSnow: Int? = null,
	val pressureMb: @RawValue  Any? = null,
	val visMiles: @RawValue  Any? = null
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
	val avgvisKm: @RawValue  Any? = null,
	val uv: @RawValue  Any? = null,
	val avgtempF: @RawValue  Any? = null,
	val avgtempC: @RawValue  Any? = null,
	val dailyChanceOfSnow: Int? = null,
	val maxtempC: @RawValue  Any? = null,
	val maxtempF: @RawValue  Any? = null,
	val mintempC: @RawValue  Any? = null,
	val avgvisMiles: @RawValue  Any? = null,
	val dailyWillItRain: Int? = null,
	val mintempF: @RawValue  Any? = null,
	val totalprecipIn: @RawValue  Any? = null,
	val totalsnowCm: @RawValue  Any? = null,
	val avghumidity: Int? = null,
	val condition: Condition? = null,
	val maxwindKph: @RawValue  Any? = null,
	val maxwindMph: @RawValue  Any? = null,
	val dailyChanceOfRain: Int? = null,
	val totalprecipMm: @RawValue  Any? = null,
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
