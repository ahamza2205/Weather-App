package com.example.feature_currentweather.presentation.presentation.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.feature_currentweather.R

/**
 * Composable function that displays a button to navigate to the 5-day forecast screen.
 *
 * @param onClick Callback when the button is clicked.
 */
@Composable
fun SeeForecastButton(onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primaryColor)),
        elevation = ButtonDefaults.buttonElevation(Dimens.CardElevation),
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.PaddingMedium)
    ) {
        Text(
            text = "See Next 5 Days Weather",
            color = colorResource(id = R.color.textPrimary),
            fontWeight = FontWeight.Bold,
            fontSize = Dimens.FontSizeMedium
        )
    }
}

/**
 * Composable function that displays a button to find another city's weather.
 *
 * @param onClick Callback when the button is clicked.
 */
@Composable
fun FindAnotherCityButton(onClick: () -> Unit) {
    Button(
        elevation = ButtonDefaults.buttonElevation(Dimens.CardElevation),
        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primaryColor)),
        onClick = onClick
    ) {
        Text(
            text = "Find Another City Weather",
            color = colorResource(id = R.color.textPrimary),
            fontWeight = FontWeight.Bold,
            fontSize = Dimens.FontSizeMedium
        )
    }
}
