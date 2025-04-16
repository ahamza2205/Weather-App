package com.example.data.helper

import Condition
import Day
import ForecastData
import ForecastDto
import ForecastdayItem
import com.example.core.serveice.models.CurrentWeatherDto
import com.example.core.serveice.models.Current
import com.example.core.serveice.models.CurrentCondition
import com.example.core.serveice.models.Location
import org.junit.Assert.assertEquals
import org.junit.Test

class HelperFunctionsTest {

    @Test
    fun `CurrentWeatherDto toDomain should map data correctly`() {
        val dto = CurrentWeatherDto(
            location = Location(name = "Cairo", country = "Egypt", localtime = "2023-10-15 14:00"),
            current = Current(
                temp_c = 25.0f,
                condition = CurrentCondition(text = "Sunny", icon = "sunny_icon"),
                humidity = 50,
                wind_kph = 15.0f
            )
        )

        val domain = dto.toDomain()

        assertEquals("Egypt", domain.country)
        assertEquals("Cairo", domain.city)
        assertEquals(25, domain.temperature)
        assertEquals("Sunny", domain.condition)
        assertEquals("sunny_icon", domain.iconUrl)
        assertEquals("2023-10-15 14:00", domain.localTime)
        assertEquals(50, domain.humidity)
        assertEquals(15, domain.wind_kph)
    }

    @Test
    fun `ForecastDto toDomain should map data correctly`() {
        val dto = ForecastDto(
            forecast = ForecastData(
                forecastday = listOf(
                    ForecastdayItem(
                        date = "2023-10-15",
                        day = Day(
                            mintemp_c = 20.0f,
                            maxtemp_c = 30.0f,
                            condition = Condition(text = "Sunny", icon = "sunny_icon")
                        )
                    )
                )
            )
        )

        val domain = dto.toDomain()

        val forecastDay = domain.forecastDays[0]
        assertEquals("2023-10-15", forecastDay.date)
        assertEquals("Sunny", forecastDay.conditionText)
        assertEquals("sunny_icon", forecastDay.conditionIcon)
        assertEquals(20.0f, forecastDay.minTemp)
        assertEquals(30.0f, forecastDay.maxTemp)
    }
}