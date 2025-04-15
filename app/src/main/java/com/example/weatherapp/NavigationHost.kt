package com.example.weatherapp

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature_cityinpu.presentation.CityInputScreen
import com.example.feature_currentweather.presentation.CurrentWeatherScreen
import com.example.feature_currentweather.presentation.CurrentWeatherViewModel
import java.net.URLEncoder

import java.nio.charset.StandardCharsets

@Composable
fun NavigationHost(startCity: String? = null) {
    val navController = rememberNavController()

    // Determine start destination based on whether a city is saved
    val startDestination = if (startCity.isNullOrBlank()) {
        Routes.CityInput
    } else {
        // Encode the city to handle special characters
        val encodedCity = URLEncoder.encode(startCity, StandardCharsets.UTF_8.toString())
        val destination = "${Routes.CurrentWeather}/$encodedCity"
        destination
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.CityInput) {
            CityInputScreen(navController = navController)
        }

        composable(
            "${Routes.CurrentWeather}/{city}",
            arguments = listOf(navArgument("city") { type = NavType.StringType })
        ) { entry ->
            val city = entry.arguments?.getString("city") ?: ""
            val viewModel: CurrentWeatherViewModel = hiltViewModel()
            if (city.isBlank() && !startCity.isNullOrBlank()) {
                CurrentWeatherScreen(city = startCity, navController = navController, viewModel = viewModel)
            } else {
                CurrentWeatherScreen(city = city, navController = navController, viewModel = viewModel)
            }
        }
    }
}