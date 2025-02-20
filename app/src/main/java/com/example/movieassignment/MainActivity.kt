package com.example.movieassignment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieassignment.Screen.DetailsScreen
import com.example.movieassignment.Screen.HomeScreen
import com.example.movieassignment.Screen.SplashScreen
import com.example.movieassignment.ui.theme.MovieAssignmentTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieAssignmentTheme {
                NavGraph()
            }
        }
    }
}

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Splash.route,
        modifier = Modifier
            .fillMaxSize()
            .padding()
    ) {
        composable(NavRoutes.Splash.route) {
            SplashScreen(navController)
        }
        composable(NavRoutes.HomeScreen.route) {
            HomeScreen(navHost = navController, viewModel = getViewModel())
        }
        composable(
            route = "${NavRoutes.DetailsScreen.route}/{titleId}",
            arguments = listOf(
                navArgument("titleId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            Log.d("detailsscreen", "NavGraph: titleId: ${backStackEntry.arguments?.getString("titleId")}")
            val titleId = backStackEntry.arguments?.getString("titleId") ?: ""
            DetailsScreen(navHost=navController, titleId = titleId, viewModel = getViewModel())
        }
    }
}