package com.example.lab1.data.network.retrofit

import com.example.lab1.domain.models.Balance
import com.example.lab1.domain.models.Tariff
import com.example.lab1.domain.models.UserInfo
import retrofit2.http.GET

interface Api {

    @GET("userInfo")
    suspend fun getUserInfo(): List<UserInfo>

    @GET("tariffs")
    suspend fun getTariffs(): List<Tariff>

    @GET("balance")
    suspend fun getBalance(): List<Balance>
}