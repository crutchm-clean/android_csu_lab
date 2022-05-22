package com.example.lab1.data.repos

import com.example.lab1.data.network.retrofit.ApiProvider
import com.example.lab1.data.room.dao.TariffDao
import com.example.lab1.data.room.models.TariffEntity
import com.example.lab1.domain.models.Tariff
import com.example.lab1.domain.repos.TariffRepository

class TariffRepositoryImpl(private val api: ApiProvider, private val dao: TariffDao) :
    TariffRepository {
    override suspend fun fetchTariff(): List<Tariff> {
        val db = dao.get()
        return if (db.isEmpty()) {
            val cloud = api.getApi().getTariffs()
            dao.save(*cloud.map { TariffEntity(it.title, it.desc, it.cost, it.id) }.toTypedArray())
            cloud
        } else {
            db.map { Tariff(it.title, it.desc, it.cost, it.id) }
        }
    }

    override suspend fun delete(id: String): List<Tariff> {
        dao.delete(id)
        return dao.get().map { Tariff(it.title, it.desc, it.cost, it.id) }
    }
}