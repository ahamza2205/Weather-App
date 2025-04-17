package com.example.feature_forecast.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.example.feature_forecast.R

/**
 * Composable for the back button to navigate to the previous screen.
 */
@Composable
 fun BackButton(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimens.PaddingSmall),
        horizontalArrangement = Arrangement.Start
    ) {
        Button(
            elevation = ButtonDefaults.buttonElevation(Dimens.CardElevation),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.primaryColor)),
            onClick = { navController.popBackStack() }
        ) {
            Text(
                text = "Back to Current Weather",
                color = colorResource(id = R.color.textPrimary),
                fontWeight = FontWeight.Bold,
                fontSize = Dimens.FontSizeMedium
            )
        }
    }
}