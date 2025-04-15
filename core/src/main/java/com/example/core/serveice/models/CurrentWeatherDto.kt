package com.example.core.serveice.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class CurrentWeatherDto(
    val current: Current? = null,
    val location: Location? = null
) : Parcelable

@Parcelize
data class Location(
    val localtime: String? = null,
    val country: String? = null,
    val localtimeEpoch: Int? = null,
    val name: String? = null,
    val lon: Double? = null,
    val region: String? = null,
    val lat: Double? = null,
    val tzId: String? = null
) : Parcelable

@Parcelize
data class Condition(
    val code: Int? = null,
    val icon: String? = null,
    val text: String? = null
) : Parcelable

@Parcelize
data class Current(
    val feelslikeC: Float? = null,
    val feelslikeF: Float? = null,
    val windDegree: Int? = null,
    val windchillF: Float? = null,
    val windchillC: Float? = null,
    val lastUpdatedEpoch: Int? = null,
    val temp_c: Float? = null,
    val tempF: Float? = null,
    val cloud: Int? = null,
    val windKph: Float? = null,
    val windMph: Float? = null,
    val humidity: Int? = null,
    val dewpointF: Float? = null,
    val uv: Float? = null,
    val lastUpdated: String? = null,
    val heatindexF: Float? = null,
    val dewpointC: Float? = null,
    val isDay: Int? = null,
    val precipIn: Float? = null,
    val heatindexC: Float? = null,
    val windDir: String? = null,
    val gustMph: Float? = null,
    val pressureIn: Float? = null,
    val gustKph: Float? = null,
    val precipMm: Float? = null,
    val condition: Condition? = null,
    val visKm: Float? = null,
    val pressureMb: Float? = null,
    val visMiles: Float? = null
) : Parcelable
