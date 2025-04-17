package com.example.feature_currentweather.presentation.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.core.serveice.models.CurrentWeather
import com.example.core.utils.WeatherFormatter
import com.example.feature_currentweather.R

/**
 * Composable for displaying a card with basic weather information.
 */
@Composable
fun WeatherInfoCard(weather: CurrentWeather) {
    Card(
        modifier = Modifier
            .padding(Dimens.PaddingMedium)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(Dimens.CardElevation),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.primaryColor))
    ) {
        Column(
            modifier = Modifier
                .padding(Dimens.PaddingMedium)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weather.country,
                color = colorResource(id = R.color.textSecondary),
                fontSize = Dimens.FontSizeExtraLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(Dimens.PaddingSmall))
            Text(
                text = weather.city,
                color = colorResource(id = R.color.textPrimary),
                style = MaterialTheme.typography.headlineSmall
            )
            AsyncImage(
                model = WeatherFormatter.getWeatherIconUrl(weather.iconUrl),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(Dimens.IconSize * 2)
            )
            Text(
                text = WeatherFormatter.formatTemperature(weather.temperature),
                color = colorResource(id = R.color.textPrimary),
                fontSize = Dimens.FontSizeExtraLarge * 2,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(Dimens.PaddingSmall))
            Text(
                text = weather.condition,
                color = colorResource(id = R.color.conditionColor),
                fontSize = Dimens.FontSizeLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}