package com.example.lab1.domain.usecases.implementations

import com.example.lab1.domain.models.Balance
import com.example.lab1.domain.repos.BalanceRepository
import com.example.lab1.domain.usecases.interfaces.GetBalanceUseCase

class GetBalanceUseCaseImpl(private val repo: BalanceRepository) : GetBalanceUseCase {
    override suspend fun getBalance(): Balance = repo.fetchBalance()
}