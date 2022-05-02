package com.example.lab1.domain.usecases.implementations

import com.example.lab1.domain.models.Tariff
import com.example.lab1.domain.repos.TariffRepository
import com.example.lab1.domain.usecases.interfaces.GetTariffUseCase

class GetTariffUseCaseImpl(private val repo: TariffRepository) : GetTariffUseCase {
    override suspend fun getTariff(): List<Tariff> = repo.fetchTariff()

}