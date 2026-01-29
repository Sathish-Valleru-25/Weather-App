package com.example.weatherapp


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.weatherapp.data.model.WeatherInfoResponse
import com.example.weatherapp.data.repos.WeatherInfoApiRepository
import com.example.weatherapp.ui.theme.WeatherUiState
import com.example.weatherapp.ui.theme.viewmodel.WeatherInfoVM
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherInfoVMTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private lateinit var weatherInfoVM: WeatherInfoVM


    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()


    private val weatherInfoApiRepository: WeatherInfoApiRepository = mockk()

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        weatherInfoVM = WeatherInfoVM(weatherInfoApiRepository)

        // Set the main dispatcher to use the test dispatcher in coroutines
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun `fetchWeatherInformation - when city is empty and cached data exists, should return cached weather info`() = runTest {

        val cachedWeatherInfo : WeatherInfoResponse = mockk()
        every { weatherInfoApiRepository.getCachedWeatherInfo() } returns flowOf(cachedWeatherInfo)

        weatherInfoVM.uiState.test {
            assertEquals(WeatherUiState.Idle, awaitItem())
            weatherInfoVM.fetchWeatherInformation("")
            val state = awaitItem() as WeatherUiState.Success
            assertEquals(cachedWeatherInfo, state.weatherInfoResponse)
        }
    }

    @Test
    fun `fetchWeatherInformation - when city is provided, should fetch weather info from API`() = runTest {
        // Given
        val city = "New York"
        val weatherInfo : WeatherInfoResponse = mockk()
        every { weatherInfoApiRepository.getWeatherInfo(city) } returns flowOf(Result.success(weatherInfo))

        weatherInfoVM.uiState.test {
             assertEquals(WeatherUiState.Idle, awaitItem())
            weatherInfoVM.fetchWeatherInformation(city)
            assertEquals(WeatherUiState.Loading, awaitItem())
            val successState = awaitItem() as WeatherUiState.Success
            assertEquals(weatherInfo, successState.weatherInfoResponse)
                  }
    }


}
