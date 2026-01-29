package com.example.weatherapp.ui.theme.screen

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.weatherapp.ui.theme.uicomponents.weatherInfoSearch
import com.example.weatherapp.ui.theme.utils.getCityName
import com.example.weatherapp.ui.theme.viewmodel.WeatherInfoVM

/**
 * The main composable for the  WeatherInfo search screen.
 */
@Composable
fun WeatherInfoScreen(
    viewModel: WeatherInfoVM =hiltViewModel()
) {

    val context = LocalContext.current
    var hasLocationPermission by remember { mutableStateOf(false) }
    var cityName = ""

    // Handle location permission request
    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if (permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) ||
                permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false)) {
                hasLocationPermission = true
            } else {
                viewModel.fetchWeatherInformation("") // fetch last stored city weather info from database if available
                Toast.makeText(context, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    )

    // Request location permission when the composable is first displayed
    LaunchedEffect(hasLocationPermission) {
        if (hasLocationPermission) {
            cityName = getCityName(context) ?: ""
                viewModel.fetchWeatherInformation(city = cityName)
        } else {
               locationPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }
    }

    weatherInfoSearch(viewModel = viewModel, cityName = cityName)

}







