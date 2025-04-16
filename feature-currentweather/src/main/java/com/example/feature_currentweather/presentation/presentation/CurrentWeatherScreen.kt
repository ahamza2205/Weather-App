package com.example.feature_currentweather.presentation.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.feature_currentweather.R

@Composable
fun CurrentWeatherScreen(
    city: String,
    navController: NavController,
    viewModel: CurrentWeatherViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadWeather(city)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        if (state.isLoading) {
            Text("Loading...")
        } else if (state.weather != null) {
            val weather = state.weather!!

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Button to find another city
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
                            fontWeight = FontWeight.Bold ,
                            fontSize = 16.sp
                        )
                    }
                }

                Text(
                    text = weather.localTime ?: "",
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
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = weather.country,
                            color = colorResource(id = R.color.textSecondary),
                            style = MaterialTheme.typography.labelLarge,
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
                            model = "https:${weather.iconUrl}",
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(200.dp)
                        )

                        Text(
                            text = "${weather.temperature} °C",
                            color = colorResource(id = R.color.textPrimary),
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            fontSize = 48.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = weather.condition,
                            color = colorResource(id = R.color.conditionColor),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.titleMedium
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
                                "${weather.humidity}%",
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
                                "${weather.wind_kph} km/h",
                                color = colorResource(id = R.color.textSecondary)
                            )
                        }
                    }
                }

                Button(
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primaryColor)),
                    elevation = ButtonDefaults.buttonElevation(8.dp),
                    onClick = {
                        navController.navigate("${"forecastScreen"}/$city")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text(
                        text = "See Next 5 Days Weather",
                        color = colorResource(id = R.color.textPrimary),
                        fontWeight = FontWeight.Bold ,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
