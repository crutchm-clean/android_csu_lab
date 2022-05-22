package com.example.lab1.domain.repos

import com.example.lab1.domain.models.Balance

interface BalanceRepository {
    suspend fun fetchBalance() : Balance


}