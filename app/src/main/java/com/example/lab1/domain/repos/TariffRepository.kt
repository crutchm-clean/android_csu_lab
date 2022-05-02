package com.example.lab1.domain.repos

import com.example.lab1.domain.models.Tariff

interface TariffRepository {
    suspend fun fetchTariff() : List<Tariff>
}