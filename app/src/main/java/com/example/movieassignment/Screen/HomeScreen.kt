package com.example.movieassignment.Screen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieassignment.NavRoutes
import com.example.movieassignment.model.BaseModel
import com.example.movieassignment.model.Releases
import org.koin.androidx.compose.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navHost: NavHostController,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    val (searchmovie, setmovie) = remember { mutableStateOf("") }
    val moviesState by viewModel.movies.collectAsState()

    var showMovies by remember { mutableStateOf(true) }
    val scaffoldState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Scaffold(
        snackbarHost = { SnackbarHost(scaffoldState) }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Movies & TV Shows",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(16.dp)
                    ),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ToggleButton("Movies", showMovies) { showMovies = true }
                ToggleButton("TV Shows", !showMovies) { showMovies = false }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(CircleShape)
                    .background(Color.White),
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = searchmovie,
                    onValueChange = setmovie,
                    placeholder = { if (showMovies) Text(text = "Search Movies") else Text(
                        text = "Search TV Shows"
                    ) }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (moviesState) {
                is BaseModel.Loading -> {
                    Spacer(modifier = Modifier.height(15.dp))
                    ShimmerGridEffect()
                }
                is BaseModel.Success -> {
                    val movieItems = (moviesState as BaseModel.Success<List<Releases>>).data

                    val filteredItems = movieItems.filter { it.Type == if (showMovies) "movie" else "tv_series" }

                    val searchFilteredItems = if (searchmovie.isNotEmpty()) {
                        filteredItems.filter {
                            it.Name.contains(searchmovie, ignoreCase = true)
                        }
                    } else {
                        filteredItems
                    }

                    AnimatedVisibility(
                        visible = searchFilteredItems.isNotEmpty(),
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxWidth()) {
                            items(searchFilteredItems) { prod ->
                                MovieCard(prod, navHost)
                            }
                        }
                    }
                }
                is BaseModel.Error -> {
                    val errorMessage = (moviesState as BaseModel.Error).error
                    Toast.makeText(context, "Error: $errorMessage", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun ToggleButton(text: String, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            color = if (isSelected) Color.White else Color.Black
        )
        AnimatedVisibility(
            visible = isSelected,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .height(3.dp)
                    .width(40.dp)
                    .background(Color.White)
            )
        }
    }
}

@Composable
fun MovieCard(prod: Releases, navHost: NavHostController) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .animateContentSize()
            .height(250.dp)
            .shadow(elevation = 8.dp, shape = RoundedCornerShape(8.dp)),
        onClick = {
            Log.d("DetailsScreenViewModel", "Fetching details for ID: ${prod.Id}")
            navHost.navigate("${NavRoutes.DetailsScreen.route}/${prod.Id}")
        }
    ) {
        AsyncImage(
            model = prod.Image,
            contentDescription = prod.Name,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
            contentScale = ContentScale.Crop,
        )
    }
}
