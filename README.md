WeatherApp

A simple weather app built with Jetpack Compose, Kotlin Coroutines, Hilt, and Flow. The app fetches weather information based on the user's city or the device's current location.

Features

Location-Based Weather: Retrieves the current location using the device's GPS and fetches the weather based on that location.

City-Based Weather: Users can search for weather information by entering a city name.

UI Built with Jetpack Compose: Fully implemented using Jetpack Compose for modern, declarative UI development.

Flow & StateFlow: Uses Flow and StateFlow for handling asynchronous data streams and managing UI states such as loading, success, and error.

Error Handling: Displays user-friendly error messages when the weather data fetch fails.

Tech Stack

Kotlin: Programming language used for building the app.

Jetpack Compose: UI toolkit for modern Android development.

Coroutines: For handling asynchronous operations like fetching weather data.

Hilt: Dependency injection framework for managing dependencies.

Flow / StateFlow: To handle UI states and asynchronous data.

Retrofit (Optional): For making network requests to fetch weather data (in case you're using a weather API like OpenWeatherMap).

Mockito / MockK: For unit testing repository and ViewModel interactions.

JUnit5: For unit testing.

Prerequisites

Before you begin, ensure you have the following installed:

Android Studio: For developing Android apps.

Kotlin: Ensure you're using the latest stable version of Kotlin.

Hilt: For dependency injection, setup as required in your project.
