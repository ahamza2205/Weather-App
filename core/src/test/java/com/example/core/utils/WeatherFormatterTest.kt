package com.example.core.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class WeatherFormatterTest {

    @Test
    fun `formatTemperature returns correct string`() {
        val result = WeatherFormatter.formatTemperature(25)
        assertEquals("25°C", result)
    }

    @Test
    fun `formatMinMaxTemp returns correct format`() {
        val result = WeatherFormatter.formatMinMaxTemp(15.5f, 28.9f)
        assertEquals("15/28°C", result)
    }

    @Test
    fun `formatHumidity returns percent correctly`() {
        val result = WeatherFormatter.formatHumidity(60)
        assertEquals("60%", result)
    }

    @Test
    fun `formatWindSpeed returns wind speed with unit`() {
        val result = WeatherFormatter.formatWindSpeed(20)
        assertEquals("20 km/h", result)
    }

    @Test
    fun `getWeatherIconUrl returns correct full url`() {
        val result = WeatherFormatter.getWeatherIconUrl("//cdn.weatherapi.com/icon.png")
        assertEquals("https://cdn.weatherapi.com/icon.png", result)
    }

    @Test
    fun `formatTime returns correctly formatted time`() {
        val input = "2024-04-16 14:30"
        val result = WeatherFormatter.formatTime(input)


        val parser = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val formatter = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val expected = formatter.format(parser.parse(input)!!)
        assertEquals(expected, result)
    }

    @Test
    fun `formatDayName returns Today for current date`() {
        val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val result = WeatherFormatter.formatDayName(today)
        assertEquals("Today", result)
    }

    @Test
    fun `formatDayName returns day of week for past date`() {
        val result = WeatherFormatter.formatDayName("2024-04-15")
        val expected = SimpleDateFormat("EEEE", Locale.getDefault()).format(
            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse("2024-04-15")!!
        )
        assertEquals(expected, result)
    }
}
