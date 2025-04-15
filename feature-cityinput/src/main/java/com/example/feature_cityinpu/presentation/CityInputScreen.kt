package com.example.feature_cityinpu.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun CityInputScreen(
    navController: NavController,
    viewModel: CityInputViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = state.cityName,
            onValueChange = { viewModel.onCityNameChanged(it) },
            label = { Text("Enter city name") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A2B38)),
            onClick = {
                if (state.cityName.isNotBlank()) {
                    viewModel.onSubmitClicked { city ->
                        navController.navigate("${"currentWeatherScreen"}/$city") {
                            // Pop back stack to prevent going back to CityInputScreen
                            popUpTo("cityInputScreen") { inclusive = true }
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Weather")
        }
    }
}