package com.example.feature_currentweather.usecase

import com.example.core.serveice.models.Current
import com.example.core.serveice.models.CurrentCondition
import com.example.core.serveice.models.CurrentWeather
import com.example.core.serveice.models.CurrentWeatherDto
import com.example.core.serveice.models.Location
import com.example.data.data.WeatherRepository
import com.example.feature_currentweather.presentation.domain.GetCurrentWeatherUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Response
import java.lang.Exception

class GetCurrentWeatherUseCaseTest {

    private lateinit var useCase: GetCurrentWeatherUseCase
    private lateinit var repository: WeatherRepository

    @Before
    fun setUp() {
        repository = Mockito.mock(WeatherRepository::class.java)
        useCase = GetCurrentWeatherUseCase(repository)
    }

    @Test
    fun `invoke should return CurrentWeather when API call is successful`(): Unit = runBlocking {
        val city = "Cairo"
        val mockDto = CurrentWeatherDto(
            location = Location(
                name = "Cairo",
                country = "Egypt",
                localtime = "2023-10-15 14:00"
            ),
            current = Current(
                temp_c = 25.0f,
                condition = CurrentCondition(text = "Sunny", icon = "sunny_icon"),
                humidity = 50,
                wind_kph = 15.0f
            )
        )
        val mockResponse = Response.success(mockDto)
        Mockito.`when`(repository.getCurrentWeather(city)).thenReturn(mockResponse)

        val expectedWeather = CurrentWeather(
            country = "Egypt",
            city = "Cairo",
            temperature = 25,
            condition = "Sunny",
            iconUrl = "sunny_icon",
            localTime = "2023-10-15 14:00",
            humidity = 50,
            wind_kph = 15
        )

        val result = useCase(city)

        assertEquals(expectedWeather, result)
        Mockito.verify(repository).getCurrentWeather(city)
    }

    @Test(expected = Exception::class)
    fun `invoke should throw Exception when API response body is null`(): Unit = runBlocking {
        val city = "Cairo"
        val mockResponse = Response.success<CurrentWeatherDto>(null)
        Mockito.`when`(repository.getCurrentWeather(city)).thenReturn(mockResponse)

        useCase(city)

        Mockito.verify(repository).getCurrentWeather(city)
    }
}