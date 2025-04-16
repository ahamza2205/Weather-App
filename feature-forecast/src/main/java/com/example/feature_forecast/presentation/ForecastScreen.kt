package com.example.feature_forecast.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.feature_forecast.R
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ForecastScreen(
    city: String,
    navController: NavController,
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.processIntent(ForecastIntent.LoadForecast(city))
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
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
                    navController.popBackStack()
                }
            ) {
                Text(
                    text = "Back to Current Weather",
                    color = colorResource(id = R.color.textPrimary),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

        }
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(color = Color.White)
                }

                state.forecast != null -> {
                    val forecastDays = state.forecast!!.forecastDays
                    val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        items(forecastDays) { day ->
                            val date =
                                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(day.date)
                            val dayName = if (day.date == today) {
                                "Today"
                            } else {
                                SimpleDateFormat("EEEE", Locale.getDefault()).format(date ?: Date())
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                shape = RoundedCornerShape(16.dp),
                                elevation = CardDefaults.cardElevation(8.dp),
                                colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.primaryColor))
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    AsyncImage(
                                        model = "https:${day.conditionIcon}",
                                        contentDescription = "Weather Icon",
                                        modifier = Modifier.size(64.dp)
                                    )

                                    Spacer(modifier = Modifier.width(16.dp))

                                    Column {
                                        Text(
                                            text = dayName,
                                            color = colorResource(id = R.color.textPrimary),
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = day.conditionText,
                                            color = colorResource(id = R.color.timeColor),
                                            fontSize = 18.sp
                                        )
                                    }

                                    Spacer(modifier = Modifier.weight(1f))

                                    Text(
                                        text = "${day.minTemp.toInt()}/${day.maxTemp.toInt()}°C",
                                        color = colorResource(id = R.color.textPrimary),
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                state.error != null -> {
                    Text(
                        text = state.error ?: "Unknown error",
                        color = Color.Red,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}