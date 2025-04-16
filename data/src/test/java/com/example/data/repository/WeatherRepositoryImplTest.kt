package com.example.data.repository


import Condition
import Day
import ForecastData

import ForecastDto
import ForecastdayItem
import com.example.core.serveice.network.WeatherApiService
import com.example.core.serveice.models.CurrentCondition
import com.example.core.serveice.models.Current
import com.example.core.serveice.models.CurrentWeatherDto
import com.example.core.serveice.models.Location
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response


class WeatherRepositoryImplTest {

    private lateinit var apiService: WeatherApiService
    private lateinit var repository: WeatherRepositoryImpl

    @Before
    fun setUp() {
        apiService = Mockito.mock(WeatherApiService::class.java)
        repository = WeatherRepositoryImpl(apiService)
    }

    @Test
    fun `getCurrentWeather should return correct response when API call is successful`(): Unit =
        runBlocking {
            val city = "Cairo"
            val mockDto = CurrentWeatherDto(
                location = Location(
                    name = "Cairo",
                    country = "Egypt",
                    localtime = "2023-10-15 14:00"
                ),
                current = Current(
                    temp_c = 25.0f,
                    condition = CurrentCondition(text = "Sunny"),
                    humidity = 50,
                    wind_kph = 15.0f
                )
            )
            val mockResponse = Response.success(mockDto)
            Mockito.`when`(apiService.getCurrentWeather(city = city)).thenReturn(mockResponse)

            val result = repository.getCurrentWeather(city)

            assertTrue(result.isSuccessful)
            assertEquals(mockDto, result.body())
            Mockito.verify(apiService).getCurrentWeather(city = city)
        }

    @Test
    fun `getForecast should return correct response when API call is successful`(): Unit =
        runBlocking {
            val city = "Cairo"
            val mockDto = ForecastDto(
                forecast = ForecastData(
                    forecastday = listOf(
                        ForecastdayItem(
                            date = "2023-10-15",
                            day = Day(
                                mintemp_c = 20.0f,
                                maxtemp_c = 30.0f,
                                condition = Condition(text = "Sunny", icon = "sunny_icon") // Use local Condition
                            )
                        )
                    )
                )
            )
            val mockResponse = Response.success(mockDto)
            Mockito.`when`(apiService.getForecast(city = city)).thenReturn(mockResponse)

            val result = repository.getForecast(city)

            assertTrue(result.isSuccessful)
            assertEquals(mockDto, result.body())
            Mockito.verify(apiService).getForecast(city = city)
        }
}