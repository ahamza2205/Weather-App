package com.example.core.serveice.models

data class CurrentWeather(
    val country: String,
    val city: String,
    val temperature: Number,
    val condition: String,
    val iconUrl: String
)
