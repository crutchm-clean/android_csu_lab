package com.example.lab1.data.repos

import com.example.lab1.data.network.retrofit.ApiProvider
import com.example.lab1.data.room.dao.BalanceDao
import com.example.lab1.data.room.models.BalanceEntity
import com.example.lab1.domain.models.Balance
import com.example.lab1.domain.repos.BalanceRepository

class BalanceRepositoryImpl(private val api: ApiProvider, private val dao: BalanceDao) : BalanceRepository {
    override suspend fun fetchBalance(): Balance {
        val db = dao.get()
        return if(db.isNotEmpty()){
            val balance = db[0]
            Balance(balance.accNum, balance.balance, balance.nextPay, balance.id)
        } else{
            val cloud = api.getApi().getBalance()[0]
            dao.save(BalanceEntity(cloud.accNum, cloud.balance, cloud.nextPay, cloud.id))
            cloud
        }
    }

}