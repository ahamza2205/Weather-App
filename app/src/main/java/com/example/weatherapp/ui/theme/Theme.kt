// Theme.kt
package com.example.weatherapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color

private val LightCustomColorScheme = lightColorScheme(
    primary = SkyBlue,
    onPrimary = TextWhite,
    secondary = SunYellow,
    onSecondary = Color.Black,
    surface = CardBackground,
    onSurface = TextWhite,
    background = Color.White,
    onBackground = Color.Black
)

private val DarkCustomColorScheme = darkColorScheme(
    primary = SkyBlue,
    onPrimary = Color.Black,
    secondary = SunYellow,
    onSecondary = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    background = Color(0xFF121212),
    onBackground = Color.White
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkCustomColorScheme
        else -> LightCustomColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
