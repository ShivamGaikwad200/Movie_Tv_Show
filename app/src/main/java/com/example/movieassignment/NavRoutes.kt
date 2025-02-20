package com.example.movieassignment

sealed class NavRoutes(val route: String) {
    object Splash : NavRoutes("splash")
    object HomeScreen : NavRoutes("movies_list")
    object DetailsScreen : NavRoutes("movies_description")
}