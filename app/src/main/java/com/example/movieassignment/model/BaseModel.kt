package com.example.movieassignment.model

sealed class BaseModel<out T> {
    data class Success<T>(val data:T):BaseModel<T>()
    data class Error(val error:String):BaseModel<Nothing>()
    object Loading:BaseModel<Nothing>()
}