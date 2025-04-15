package com.example.feature_cityinpu.presentation

import androidx.lifecycle.ViewModel
import com.example.feature_cityinpu.domain.SaveCityInputUseCase
import com.example.feature_cityinpu.domain.model.CityInput
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CityInputViewModel @Inject constructor(
    private val saveCityInputUseCase: SaveCityInputUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CityInputState())
    val state: StateFlow<CityInputState> = _state

    fun onCityNameChanged(name: String) {
        _state.value = _state.value.copy(cityName = name)
    }

    fun onSubmitClicked(onSubmit: (String) -> Unit) {
        val cityName = _state.value.cityName
        saveCityInputUseCase(CityInput(cityName))
        onSubmit(cityName)
    }
}

