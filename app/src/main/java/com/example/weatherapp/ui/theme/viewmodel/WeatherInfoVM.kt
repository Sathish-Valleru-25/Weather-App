package com.example.weatherapp.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.repos.WeatherInfoApiRepository
import com.example.weatherapp.ui.theme.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherInfoVM @Inject constructor(
    private val weatherInfoApiRepository: WeatherInfoApiRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Idle)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    fun fetchWeatherInformation(city: String) {

        if (_uiState.value is WeatherUiState.Loading) {
            return
        }


        viewModelScope.launch {

            // If city is empty and last stored weather info is available, fetch it from database. this required only when app launch
            if(city.isEmpty()){
                weatherInfoApiRepository.getCachedWeatherInfo().collect{
                    if(it != null){
                        _uiState.value = WeatherUiState.Success(it)
                    }
                }
            } else {
                // Fetch weather info from API
                weatherInfoApiRepository.getWeatherInfo(city)
                    .onStart {
                        _uiState.value = WeatherUiState.Loading  // Set loading state
                    }.catch { exception ->
                        _uiState.value = WeatherUiState.Error("An unexpected error occurred: ${exception.message}")
                    }
                    .collect { result ->
                        // The result from the repository is either a success or a failure
                        result.onSuccess { weatherInfo ->
                            _uiState.value = WeatherUiState.Success(weatherInfo)

                        }.onFailure { exception ->
                            _uiState.value = WeatherUiState.Error(exception.message ?: "An unknown error occurred")
                        }
                    }
            }

        }
    }

}