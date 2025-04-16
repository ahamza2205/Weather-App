package com.example.feature_forecast.viewmodel

import Forecast
import ForecastDay
import app.cash.turbine.test
import com.example.feature_forecast.domain.GetForecastUseCase
import com.example.feature_forecast.presentation.ForecastIntent
import com.example.feature_forecast.presentation.ForecastState
import com.example.feature_forecast.presentation.ForecastViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ForecastViewModelTest {

    private lateinit var viewModel: ForecastViewModel
    private val getForecastUseCase: GetForecastUseCase = mockk()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ForecastViewModel(getForecastUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be empty with no loading or error`() = testScope.runTest {
        viewModel.state.test {
            assertEquals(ForecastState(isLoading = false, forecast = null, error = null), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `processIntent LoadForecast should emit loading then success`() = testScope.runTest {
        val fakeForecast = Forecast(
            forecastDays = listOf(
                ForecastDay(
                    date = "2025-04-17",
                    conditionText = "Sunny",
                    conditionIcon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
                    minTemp = 15f,
                    maxTemp = 25f
                )
            )
        )

        coEvery { getForecastUseCase("Cairo") } returns fakeForecast

        viewModel.state.test {
            assertEquals(ForecastState(isLoading = false, forecast = null, error = null), awaitItem())
            viewModel.processIntent(ForecastIntent.LoadForecast("Cairo"))
            assertEquals(ForecastState(isLoading = true, forecast = null, error = null), awaitItem())
            assertEquals(ForecastState(forecast = fakeForecast, isLoading = false, error = null), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `processIntent LoadForecast should emit loading then error when use case fails`() = testScope.runTest {
        val errorMessage = "Network error"
        coEvery { getForecastUseCase("Cairo") } throws Exception(errorMessage)

        viewModel.state.test {
            assertEquals(ForecastState(isLoading = false, forecast = null, error = null), awaitItem())
            viewModel.processIntent(ForecastIntent.LoadForecast("Cairo"))
            assertEquals(ForecastState(isLoading = true, forecast = null, error = null), awaitItem())
            assertEquals(ForecastState(isLoading = false, forecast = null, error = errorMessage), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `processIntent LoadForecast with empty city should emit error without calling use case`() = testScope.runTest {
        viewModel.state.test {
            assertEquals(ForecastState(isLoading = false, forecast = null, error = null), awaitItem())
            viewModel.processIntent(ForecastIntent.LoadForecast(""))
            assertEquals(ForecastState(isLoading = false, forecast = null, error = "City cannot be empty"), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}