package com.example.feature_cityinpu.usecase


import com.example.data.cache.CityInputCache
import com.example.feature_cityinpu.domain.model.CityInput
import com.example.feature_cityinpu.domain.usecase.SaveCityInputUseCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class SaveCityInputUseCaseTest {

    private lateinit var useCase: SaveCityInputUseCase
    private lateinit var cityInputCache: CityInputCache

    @Before
    fun setUp() {
        cityInputCache = Mockito.mock(CityInputCache::class.java)
        useCase = SaveCityInputUseCase(cityInputCache)
    }

    @Test
    fun `invoke should call cityInputCache saveCity with correct city name`() {
        val cityInput = CityInput(cityName = "Cairo")

        useCase(cityInput)

        Mockito.verify(cityInputCache).saveCity("Cairo")
    }
}