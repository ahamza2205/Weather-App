package com.example.feature_forecast.presentation.ui

import ForecastDay
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.core.utils.NetworkUtils
import com.example.feature_forecast.R
import com.example.feature_forecast.presentation.viewmodel.ForecastIntent
import com.example.feature_forecast.presentation.viewmodel.ForecastState
import com.example.feature_forecast.presentation.viewmodel.ForecastViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

/**
 * Main screen for displaying the weather forecast for a given city.
 * @param city The city name to fetch the forecast for.
 * @param navController Navigation controller to handle back navigation.
 * @param viewModel ViewModel for managing forecast data and state.
 */
@Composable
fun ForecastScreen(
    city: String,
    navController: NavController,
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val isConnected by NetworkUtils.isConnected.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)

    // Handle network connectivity changes
    LaunchedEffect(isConnected) {
        if (isConnected) {
            viewModel.processIntent(ForecastIntent.LoadForecast(city))
        } else {
            snackbarHostState.showSnackbar("Check your internet connection")
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(Dimens.PaddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BackButton(navController = navController)
            SwipeRefresh(
                state = swipeRefreshState,
                onRefresh = { viewModel.processIntent(ForecastIntent.LoadForecast(city)) }
            ) {
                ForecastContent(state = state)
            }
        }
    }
}

/**
 * Composable for displaying the forecast content based on the state.
 */
@Composable
private fun ForecastContent(state: ForecastState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(color = colorResource(id = R.color.textPrimary))
            }
            state.forecast != null -> {
                if (state.forecast.forecastDays.isEmpty()) {
                    EmptyStateMessage()
                } else {
                    ForecastList(forecastDays = state.forecast.forecastDays)
                }
            }
            state.error != null -> {
                ErrorMessage(error = state.error)
            }
        }
    }
}

/**
 * Composable for displaying the forecast list.
 */
@Composable
private fun ForecastList(forecastDays: List<ForecastDay>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(Dimens.PaddingMedium)
    ) {
        items(forecastDays) { day ->
            ForecastCard(day = day)
        }
    }
}
