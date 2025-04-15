package com.example.data.cache

interface CityInputCache {
    fun saveCity(city: String)
    fun getLastCity(): String?
}
