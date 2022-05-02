package com.example.lab1.data.network.retrofit

import retrofit2.Retrofit

class RetrofitClient {
    fun getClient(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .build()
}