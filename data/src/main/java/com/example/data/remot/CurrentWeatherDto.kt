package com.example.data.remot

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

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
    val lon: @RawValue Any? = null,
    val region: String? = null,
    val lat: @RawValue Any? = null,
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
    val feelslikeC: @RawValue Any? = null,
    val feelslikeF: @RawValue Any? = null,
    val windDegree: Int? = null,
    val windchillF: @RawValue Any? = null,
    val windchillC: @RawValue Any? = null,
    val lastUpdatedEpoch: Int? = null,
    val tempC: @RawValue Any? = null,
    val tempF: @RawValue Any? = null,
    val cloud: Int? = null,
    val windKph: @RawValue Any? = null,
    val windMph: @RawValue Any? = null,
    val humidity: Int? = null,
    val dewpointF: @RawValue Any? = null,
    val uv: @RawValue Any? = null,
    val lastUpdated: String? = null,
    val heatindexF: @RawValue Any? = null,
    val dewpointC: @RawValue Any? = null,
    val isDay: Int? = null,
    val precipIn: @RawValue Any? = null,
    val heatindexC: @RawValue Any? = null,
    val windDir: String? = null,
    val gustMph: @RawValue Any? = null,
    val pressureIn: @RawValue Any? = null,
    val gustKph: @RawValue Any? = null,
    val precipMm: @RawValue Any? = null,
    val condition: Condition? = null,
    val visKm: @RawValue Any? = null,
    val pressureMb: @RawValue Any? = null,
    val visMiles: @RawValue Any? = null
) : Parcelable
