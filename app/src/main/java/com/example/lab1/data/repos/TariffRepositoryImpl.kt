package com.example.lab1.data.repos

import com.example.lab1.data.network.retrofit.ApiProvider
import com.example.lab1.domain.models.Tariff
import com.example.lab1.domain.repos.TariffRepository

class TariffRepositoryImpl(private val api: ApiProvider) : TariffRepository {
    override suspend fun fetchTariff(): List<Tariff> = api.getApi().getTariffs()
}