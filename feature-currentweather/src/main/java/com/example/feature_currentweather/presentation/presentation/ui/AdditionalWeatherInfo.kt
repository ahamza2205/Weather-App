package com.example.feature_currentweather.presentation.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.core.serveice.models.CurrentWeather
import com.example.core.utils.WeatherFormatter
import com.example.feature_currentweather.R

/**
 * Composable for displaying additional weather information like humidity and wind speed.
 */
@Composable
fun AdditionalWeatherInfo(weather: CurrentWeather) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.PaddingMedium),
        horizontalArrangement = Arrangement.spacedBy(Dimens.PaddingMedium)
    ) {
        Card(
            modifier = Modifier
                .weight(1f)
                .height(100.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(Dimens.CardElevation),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.primaryColor))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.PaddingSmall),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("💧", fontSize = Dimens.FontSizeExtraLarge)
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
            elevation = CardDefaults.cardElevation(Dimens.CardElevation),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.primaryColor))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimens.PaddingSmall),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("🌬️", fontSize = Dimens.FontSizeExtraLarge)
                Text("Wind", color = colorResource(id = R.color.textPrimary))
                Text(
                    text = WeatherFormatter.formatWindSpeed(weather.wind_kph),
                    color = colorResource(id = R.color.textSecondary)
                )
            }
        }
    }
}
