package com.example.weatherapp

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

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.CityInput
    ) {
        composable(Routes.CityInput) {
            CityInputScreen(navController)
        }

        composable(
            "${Routes.CurrentWeather}/{city}",
            arguments = listOf(navArgument("city") { type = NavType.StringType })
        ) { entry ->
            val city = entry.arguments?.getString("city") ?: ""
            val viewModel: CurrentWeatherViewModel = hiltViewModel()

            CurrentWeatherScreen(city, viewModel)
        }
    }
}
