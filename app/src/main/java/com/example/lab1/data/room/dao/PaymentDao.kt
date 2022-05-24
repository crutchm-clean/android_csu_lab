package com.example.lab1.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.lab1.data.room.models.PaymentEntity

@Dao
interface PaymentDao {

    @Insert
    suspend fun add(vararg paymentEntity: PaymentEntity)

    @Query("select * from PaymentEntity")
    suspend fun getAll() : List<PaymentEntity>

    @Delete
    suspend fun deleteEntity(paymentEntity: PaymentEntity)
}