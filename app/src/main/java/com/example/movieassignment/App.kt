package com.example.movieassignment

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.movieassignment.Repo.MoviesRepo
import com.example.movieassignment.Screen.DetailsScreenViewModel
import com.example.movieassignment.Screen.HomeScreenViewModel
import com.example.movieassignment.network.API
import com.example.movieassignment.network.Header
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App:Application(){
    override fun onCreate(){
        super.onCreate()
        startKoin{
            androidContext(this@App)
            modules(module{
                single {
                    val client = OkHttpClient.Builder()
                        .addInterceptor(Header())
                        .build()
                    Retrofit
                        .Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .baseUrl("https://api.watchmode.com/v1/")
                        .build()
                }
                single {
                    val retrofit: Retrofit = get()
                    retrofit.create(API::class.java)
                }
                single {
                    MoviesRepo(get())
                }
                viewModel{
                    HomeScreenViewModel()
                }
                viewModel{
                    DetailsScreenViewModel()
                }
            })
        }
    }
}