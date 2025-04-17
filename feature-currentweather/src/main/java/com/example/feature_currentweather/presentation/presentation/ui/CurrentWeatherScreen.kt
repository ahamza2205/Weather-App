package com.example.feature_currentweather.presentation.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.utils.NetworkUtils
import com.example.core.utils.WeatherFormatter
import com.example.feature_currentweather.R
import com.example.feature_currentweather.presentation.presentation.viewmodel.CurrentWeatherViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * Main composable function for displaying the current weather screen.
 * Includes swipe-to-refresh, error handling, and navigation to other screens.
 *
 * @param city The name of the city for which weather data is displayed.
 * @param navController Navigation controller to handle screen transitions.
 * @param viewModel ViewModel for managing current weather state and logic.
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CurrentWeatherScreen(
    city: String,
    navController: NavController,
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val isConnected by NetworkUtils.isConnected.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)

    LaunchedEffect(isConnected, state.error) {
        if (isConnected && state.weather == null && !state.isLoading) {
            viewModel.loadWeather(city)
        }
        state.error?.let {
            viewModel.showError("Check your internet connection")
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(viewModel.snackbarHostState) }
    ) { innerPadding ->
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                viewModel.loadWeather(city)
            },
            modifier = Modifier.padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.PaddingMedium),
                contentAlignment = Alignment.TopCenter
            ) {
                when {
                    state.isLoading && state.weather == null -> LoadingIndicator()
                    state.error != null -> ErrorMessage(
                        message = state.error ?: "Unknown error",
                        onRetry = { viewModel.loadWeather(city) }
                    )
                    state.weather != null -> {
                        val weather = state.weather
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = Dimens.PaddingSmall),
                                horizontalArrangement = Arrangement.Start
                            ) {
                                FindAnotherCityButton {
                                    navController.navigate("cityInputScreen")
                                }
                            }
                            TimeDisplay(WeatherFormatter.formatTime(weather?.localTime ?: ""))
                            weather?.let {
                                WeatherInfoCard(it)
                                AdditionalWeatherInfo(it)
                            }
                            SeeForecastButton {
                                navController.navigate("forecastScreen/$city")
                            }
                        }
                    }
                }
            }
        }
    }
}

/**
 * Composable function to display the current local time.
 *
 * @param time The formatted time string to be displayed.
 */
@Composable
fun TimeDisplay(time: String) {
    Text(
        text = time,
        color = colorResource(id = R.color.timeColor),
        fontSize = Dimens.FontSizeExtraLarge,
        modifier = Modifier.padding(vertical = Dimens.PaddingSmall)
    )
}

