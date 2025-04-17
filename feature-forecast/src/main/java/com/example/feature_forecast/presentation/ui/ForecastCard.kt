package com.example.feature_forecast.presentation.ui

import ForecastDay
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import coil.compose.AsyncImage
import com.example.core.utils.WeatherFormatter
import com.example.feature_forecast.R

/**
 * Composable for displaying a single forecast day card.
 */
@Composable
fun ForecastCard(day: ForecastDay) {
    val dayName = WeatherFormatter.formatDayName(day.date)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.PaddingSmall),
        shape = RoundedCornerShape(Dimens.PaddingMedium),
        elevation = CardDefaults.cardElevation(Dimens.CardElevation),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.primaryColor))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PaddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = WeatherFormatter.getWeatherIconUrl(day.conditionIcon),
                contentDescription = "Weather Icon",
                modifier = Modifier.size(Dimens.IconSize)
            )
            Spacer(modifier = Modifier.width(Dimens.PaddingMedium))
            Column {
                Text(
                    text = dayName,
                    color = colorResource(id = R.color.textPrimary),
                    fontSize = Dimens.FontSizeExtraLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = day.conditionText,
                    color = colorResource(id = R.color.timeColor),
                    fontSize = Dimens.FontSizeLarge
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = WeatherFormatter.formatMinMaxTemp(day.minTemp, day.maxTemp),
                color = colorResource(id = R.color.textPrimary),
                fontSize = Dimens.FontSizeExtraLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}