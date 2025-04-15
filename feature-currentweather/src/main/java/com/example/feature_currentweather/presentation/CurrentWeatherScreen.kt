package com.example.feature_currentweather.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

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
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A2B38)),
                        onClick = {
                            navController.navigate("cityInputScreen")
                        }
                    ) {
                        Text(
                            text = "Find Another City Weather",
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }
                }
                
                Text(
                    text = weather.localTime ?: "",
                    color = Color.Gray,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
                )

                Card(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A2B38))
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
                            color = Color.LightGray,
                            style = MaterialTheme.typography.labelLarge
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = weather.city,
                            color = Color.White,
                            style = MaterialTheme.typography.headlineSmall
                        )

                        AsyncImage(
                            model = "https:${weather.iconUrl}",
                            contentDescription = "Weather Icon",
                            modifier = Modifier.size(200.dp)
                        )

                        Text(
                            text = "${weather.temperature} °C",
                            color = Color.White,
                            style = MaterialTheme.typography.headlineLarge,
                            fontWeight = FontWeight.Bold,
                            fontSize = 48.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = weather.condition,
                            color = Color(0xFF4FC3F7),
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A2B38))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("💧", fontSize = 28.sp)
                            Text("Humidity", color = Color.White)
                            Text("${weather.humidity}%", color = Color.LightGray)
                        }
                    }

                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .height(100.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(8.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A2B38))
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text("🌬️", fontSize = 28.sp)
                            Text("Wind", color = Color.White)
                            Text("${weather.wind_kph} km/h", color = Color.LightGray)
                        }
                    }
                }
            }
        }
    }
}
