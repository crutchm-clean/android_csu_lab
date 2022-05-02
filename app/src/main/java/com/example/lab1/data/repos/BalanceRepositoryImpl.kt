package com.example.lab1.data.repos

import com.example.lab1.data.network.retrofit.ApiProvider
import com.example.lab1.domain.models.Balance
import com.example.lab1.domain.repos.BalanceRepository

class BalanceRepositoryImpl(private val api: ApiProvider) : BalanceRepository {
    override suspend fun fetchBalance(): Balance = api.getApi().getBalance()[0]

}