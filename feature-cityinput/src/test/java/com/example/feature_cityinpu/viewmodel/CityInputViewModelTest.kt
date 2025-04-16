package com.example.feature_cityinpu.viewmodel


import com.example.feature_cityinpu.domain.model.CityInput
import com.example.feature_cityinpu.domain.usecase.SaveCityInputUseCase
import com.example.feature_cityinpu.presentation.CityInputViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class CityInputViewModelTest {

    private lateinit var viewModel: CityInputViewModel
    private lateinit var saveCityInputUseCase: SaveCityInputUseCase

    @Before
    fun setUp() {
        saveCityInputUseCase = Mockito.mock(SaveCityInputUseCase::class.java)
        viewModel = CityInputViewModel(saveCityInputUseCase)
    }

    @Test
    fun `onCityNameChanged should update state with new city name`() {
        val newCityName = "Cairo"

        viewModel.onCityNameChanged(newCityName)

        val state = viewModel.state.value
        assertEquals(newCityName, state.cityName)
    }

    @Test
    fun `onSubmitClicked should call use case and invoke callback with city name`() {
        val cityName = "Cairo"
        viewModel.onCityNameChanged(cityName)
        val callback: (String) -> Unit = Mockito.mock()

        viewModel.onSubmitClicked(callback)

        Mockito.verify(saveCityInputUseCase).invoke(CityInput(cityName))
        Mockito.verify(callback).invoke(cityName)
    }
}