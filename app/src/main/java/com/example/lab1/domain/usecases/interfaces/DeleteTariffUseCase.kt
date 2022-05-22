package com.example.lab1.domain.usecases.interfaces

import com.example.lab1.domain.models.Tariff

interface DeleteTariffUseCase {
    suspend operator fun invoke(id: String) : List<Tariff>
}