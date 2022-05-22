package com.example.lab1.domain.usecases.implementations

import com.example.lab1.domain.models.Tariff
import com.example.lab1.domain.repos.TariffRepository
import com.example.lab1.domain.usecases.interfaces.DeleteTariffUseCase

class DeleteTariffUseCaseImpl(private val repo: TariffRepository) : DeleteTariffUseCase {

    override suspend fun invoke(id : String) : List<Tariff> {
        return repo.delete(id)
    }
}