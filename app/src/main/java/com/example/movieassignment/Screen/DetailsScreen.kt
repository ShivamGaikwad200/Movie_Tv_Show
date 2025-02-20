package com.example.movieassignment.Screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieassignment.model.BaseModel
import com.example.movieassignment.model.TitleResponse
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navHost: NavHostController,
    titleId: String,
    viewModel: DetailsScreenViewModel = koinViewModel()
) {
    val movieDescState by viewModel.moviedesc.collectAsState()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(titleId) {
        delay(1000)
        viewModel.getMovieDetails(titleId)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            when (movieDescState) {
                is BaseModel.Success<*> -> {
                    val movie = (movieDescState as BaseModel.Success<TitleResponse>).data
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = movie.Title,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        model = movie.PosterURL,
                        contentDescription = movie.Title
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "About: ${movie.Description}")
                    Spacer(modifier = Modifier.height(16.dp))
                    movie.ReleaseDate?.let {
                        Text(text = "Release Date: $it")
                    }
                }
                is BaseModel.Error -> {
                    val errorMessage = (movieDescState as BaseModel.Error).error

                    Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
                }
                is BaseModel.Loading -> {
                    Spacer(modifier = Modifier.height(25.dp))
                    Column {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(30.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .shimmerEffect()
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(20.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .shimmerEffect()
                        )
                    }
                }
            }
        }
    }
}
