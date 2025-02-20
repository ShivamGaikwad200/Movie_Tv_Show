package com.example.movieassignment.network

import okhttp3.Interceptor
import okhttp3.Response

class Header: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Content-Type","application/json")
        return chain.proceed(requestBuilder.build())
    }
}