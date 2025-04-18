package com.example.feature_currentweather.presentation.presentation.viewmodel

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_currentweather.presentation.domain.GetCurrentWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentWeatherViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CurrentWeatherState())
    val state: StateFlow<CurrentWeatherState> = _state

    val snackbarHostState = SnackbarHostState()

    fun loadWeather(city: String) {
        if (city.isBlank()) {
            _state.value = CurrentWeatherState(isLoading = false, weather = null, error = "City cannot be empty")
            return
        }
        viewModelScope.launch {
            _state.value = CurrentWeatherState(isLoading = true)
            try {
                val response = getCurrentWeatherUseCase(city)
                _state.value = CurrentWeatherState(weather = response, isLoading = false)
                Log.d("CurrentWeatherViewModel", "Weather loaded: $response")
            } catch (e: Exception) {
                _state.value = CurrentWeatherState(error = e.message ?: "An error occurred", isLoading = false)
            }
        }
    }

    fun showError(message: String) {
        viewModelScope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }
}