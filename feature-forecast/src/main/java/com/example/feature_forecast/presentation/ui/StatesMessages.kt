package com.example.feature_forecast.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.feature_forecast.R

/**
 * Composable for displaying an error message.
 */
@Composable
 fun ErrorMessage(error: String?) {
    Text(
        text = error ?: "Unknown error",
        color = Color.Red, // Replace with R.color.errorColor if defined
        fontSize = Dimens.FontSizeLarge
    )
}

/**
 * Composable for displaying an empty state message.
 */
@Composable
 fun EmptyStateMessage() {
    Text(
        text = "No forecast data available",
        color = colorResource(id = R.color.textPrimary),
        fontSize = Dimens.FontSizeLarge
    )
}