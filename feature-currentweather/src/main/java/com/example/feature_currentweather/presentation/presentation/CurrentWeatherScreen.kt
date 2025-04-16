package com.example.feature_currentweather.presentation.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.core.utils.NetworkUtils
import com.example.core.utils.WeatherFormatter
import com.example.feature_currentweather.R
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CurrentWeatherScreen(
    city: String,
    navController: NavController,
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val isConnected by NetworkUtils.isConnected.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)

    LaunchedEffect(Unit) {
        viewModel.loadWeather(city)
    }

    LaunchedEffect(isConnected) {
        if (isConnected && state.weather == null && !state.isLoading) {
            viewModel.loadWeather(city)
        }
    }

    LaunchedEffect(state.error) {
        state.error?.let {
            snackbarHostState.showSnackbar("Check your internet connection")
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
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
                    .padding(16.dp),
                contentAlignment = Alignment.TopCenter
            ) {
                if (state.isLoading && state.weather == null) {
                    Text("Loading...")
                } else if (state.weather != null) {
                    val weather = state.weather!!

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Button(
                                elevation = ButtonDefaults.buttonElevation(8.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primaryColor)),
                                onClick = {
                                    navController.navigate("cityInputScreen")
                                }
                            ) {
                                Text(
                                    text = "Find Another City Weather",
                                    color = colorResource(id = R.color.textPrimary),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        }

                        Text(
                            text = WeatherFormatter.formatTime(weather.localTime ?: ""),
                            color = colorResource(id = R.color.timeColor),
                            fontSize = 32.sp,
                            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                        )

                        Card(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.primaryColor))
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(24.dp)
                                    .fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = weather.country,
                                    color = colorResource(id = R.color.textSecondary),
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = weather.city,
                                    color = colorResource(id = R.color.textPrimary),
                                    style = MaterialTheme.typography.headlineSmall
                                )

                                AsyncImage(
                                    model = WeatherFormatter.getWeatherIconUrl(weather.iconUrl),
                                    contentDescription = "Weather Icon",
                                    modifier = Modifier.size(200.dp)
                                )

                                Text(
                                    text = WeatherFormatter.formatTemperature(weather.temperature),
                                    color = colorResource(id = R.color.textPrimary),
                                    fontSize = 48.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = weather.condition,
                                    color = colorResource(id = R.color.conditionColor),
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(100.dp),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(8.dp),
                                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.primaryColor))
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text("💧", fontSize = 28.sp)
                                    Text("Humidity", color = colorResource(id = R.color.textPrimary))
                                    Text(
                                        text = WeatherFormatter.formatHumidity(weather.humidity),
                                        color = colorResource(id = R.color.textSecondary)
                                    )
                                }
                            }

                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(100.dp),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(8.dp),
                                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.primaryColor))
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text("🌬️", fontSize = 28.sp)
                                    Text("Wind", color = colorResource(id = R.color.textPrimary))
                                    Text(
                                        text = WeatherFormatter.formatWindSpeed(weather.wind_kph),
                                        color = colorResource(id = R.color.textSecondary)
                                    )
                                }
                            }
                        }

                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primaryColor)),
                            elevation = ButtonDefaults.buttonElevation(8.dp),
                            onClick = {
                                navController.navigate("forecastScreen/$city")
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, bottom = 16.dp)
                        ) {
                            Text(
                                text = "See Next 5 Days Weather",
                                color = colorResource(id = R.color.textPrimary),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}
