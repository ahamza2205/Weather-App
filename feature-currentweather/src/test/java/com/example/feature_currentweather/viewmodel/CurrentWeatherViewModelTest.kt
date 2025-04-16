package com.example.feature_currentweather.viewmodel



import app.cash.turbine.test
import com.example.core.serveice.models.CurrentWeather
import com.example.feature_currentweather.presentation.domain.GetCurrentWeatherUseCase
import com.example.feature_currentweather.presentation.presentation.CurrentWeatherViewModel
import com.example.feature_currentweather.presentation.presentation.CurrentWeatherState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class CurrentWeatherViewModelTest {

    private lateinit var viewModel: CurrentWeatherViewModel
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase = mockk()

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CurrentWeatherViewModel(getCurrentWeatherUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state should be empty with no loading or error`() = testScope.runTest {
        viewModel.state.test {
            assertEquals(CurrentWeatherState(isLoading = false, weather = null, error = null), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `loadWeather should emit loading then success`() = testScope.runTest {
        val fakeWeather = CurrentWeather(
            country = "Egypt",
            city = "Cairo",
            temperature = 30,
            condition = "Sunny",
            iconUrl = "//cdn.weatherapi.com/weather/64x64/day/113.png",
            localTime = "2025-04-16 12:00",
            humidity = 50,
            wind_kph = 10
        )

        coEvery { getCurrentWeatherUseCase("Cairo") } returns fakeWeather

        viewModel.state.test {
            assertEquals(CurrentWeatherState(isLoading = false, weather = null, error = null), awaitItem())
            viewModel.loadWeather("Cairo")
            assertEquals(CurrentWeatherState(isLoading = true, weather = null, error = null), awaitItem())
            assertEquals(CurrentWeatherState(weather = fakeWeather, isLoading = false, error = null), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `loadWeather should emit loading then error when use case fails`() = testScope.runTest {
        val errorMessage = "Network error"
        coEvery { getCurrentWeatherUseCase("Cairo") } throws Exception(errorMessage)

        viewModel.state.test {
            assertEquals(CurrentWeatherState(isLoading = false, weather = null, error = null), awaitItem())
            viewModel.loadWeather("Cairo")
            assertEquals(CurrentWeatherState(isLoading = true, weather = null, error = null), awaitItem())
            assertEquals(CurrentWeatherState(isLoading = false, weather = null, error = errorMessage), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `loadWeather with empty city should not call use case and emit error`() = testScope.runTest {
        viewModel.state.test {
            assertEquals(CurrentWeatherState(isLoading = false, weather = null, error = null), awaitItem())
            viewModel.loadWeather("")
            assertEquals(CurrentWeatherState(isLoading = false, weather = null, error = "City cannot be empty"), awaitItem())
            cancelAndConsumeRemainingEvents()
        }
    }
}


