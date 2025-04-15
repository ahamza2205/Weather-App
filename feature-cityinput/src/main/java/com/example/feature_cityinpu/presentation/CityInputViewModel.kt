package com.example.feature_cityinpu.presentation

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CityInputViewModel @Inject constructor() : ViewModel() {
    var city = mutableStateOf("")
}
