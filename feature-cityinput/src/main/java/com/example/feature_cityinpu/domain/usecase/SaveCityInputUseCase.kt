package com.example.feature_cityinpu.domain.usecase


import com.example.data.cache.CityInputCache
import com.example.feature_cityinpu.domain.model.CityInput
import javax.inject.Inject

class SaveCityInputUseCase @Inject constructor(
    private val cityInputCache: CityInputCache
) {
    operator fun invoke(cityInput: CityInput) {
        cityInputCache.saveCity(cityInput.cityName)
    }
}
