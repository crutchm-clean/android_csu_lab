package com.example.lab1.domain.usecases.interfaces

import com.example.lab1.domain.models.Tariff

interface GetTariffUseCase {
    suspend fun getTariff() : List<Tariff>
}