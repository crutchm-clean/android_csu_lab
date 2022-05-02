package com.example.lab1.domain.usecases.interfaces

import com.example.lab1.domain.models.Balance

interface GetBalanceUseCase {
    suspend fun getBalance() : Balance
}