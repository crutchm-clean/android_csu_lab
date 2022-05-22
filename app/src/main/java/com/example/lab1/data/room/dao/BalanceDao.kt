package com.example.lab1.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lab1.data.room.models.BalanceEntity

@Dao
interface BalanceDao {

    @Insert
    suspend fun save(vararg balance: BalanceEntity)

    @Query("select * from BalanceEntity")
    suspend fun get(): List<BalanceEntity>
}