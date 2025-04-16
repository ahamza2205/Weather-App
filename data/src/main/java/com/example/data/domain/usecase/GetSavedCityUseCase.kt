package com.example.data.domain.usecase

import com.example.data.cache.CityInputCache
import javax.inject.Inject

class GetSavedCityUseCase @Inject constructor(
    private val cache: CityInputCache
) {
    operator fun invoke(): String? = cache.getLastCity()
}
