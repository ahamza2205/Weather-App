package com.example.feature_forecast.usecase

import Condition
import Day
import ForecastData
import ForecastDto
import ForecastdayItem
import com.example.core.utils.toDomain
import com.example.data.repository.WeatherRepository
import com.example.feature_forecast.domain.GetForecastUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class GetForecastUseCaseTest {

    private lateinit var useCase: GetForecastUseCase
    private val repository: WeatherRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetForecastUseCase(repository)
    }

    @Test
    fun `invoke should return forecast when repository returns successful response`() = runTest {
        val fakeForecastDto = ForecastDto(
            forecast = ForecastData(
                forecastday = listOf(
                    ForecastdayItem(
                        date = "2025-04-17",
                        day = Day(
                            mintemp_c = 15f,
                            maxtemp_c = 25f,
                            condition = Condition(text = "Sunny", icon = "//cdn.weatherapi.com/weather/64x64/day/113.png")
                        )
                    )
                )
            )
        )
        val expectedForecast = fakeForecastDto.toDomain()
        coEvery { repository.getForecast("Cairo") } returns Response.success(fakeForecastDto)

        val result = useCase("Cairo")

        assertEquals(expectedForecast, result)
    }

    @Test(expected = Exception::class)
    fun `invoke should throw exception when repository returns unsuccessful response`() = runTest {
        coEvery { repository.getForecast("Cairo") } returns Response.error(404, "".toResponseBody())

        useCase("Cairo")
    }

    @Test(expected = Exception::class)
    fun `invoke should throw exception when repository returns null body`() = runTest {
        coEvery { repository.getForecast("Cairo") } returns Response.success(null)

        useCase("Cairo")
    }
}