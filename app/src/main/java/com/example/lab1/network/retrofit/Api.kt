package com.example.lab1.network.retrofit

import com.example.lab1.network.models.Balance
import com.example.lab1.network.models.Tariff
import com.example.lab1.network.models.UserInfo
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("userInfo")
    fun getUserInfo(): Call<List<UserInfo>>

    @GET("tariffs")
    fun getTariffs(): Call<List<Tariff>>

    @GET("balance")
    fun getBalance(): Call<List<Balance>>
}