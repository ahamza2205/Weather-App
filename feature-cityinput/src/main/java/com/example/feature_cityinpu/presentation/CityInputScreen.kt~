package com.example.feature_cityinpu.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feature_cityinput.R

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
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = colorResource(id = R.color.textPrimary),
                unfocusedIndicatorColor = colorResource(id = R.color.textPrimary),
                focusedContainerColor = colorResource(id = R.color.primaryColor),
                unfocusedContainerColor = colorResource(id = R.color.primaryColor)
            ),
            onValueChange = { viewModel.onCityNameChanged(it) },
            label = {
                Text(
                    text = "Enter city name", color = colorResource(id = R.color.textPrimary),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            elevation = ButtonDefaults.buttonElevation(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primaryColor)),
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
            Text(
                text = "Get Weather", color = colorResource(id = R.color.textPrimary),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }
    }
}