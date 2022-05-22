package com.example.lab1.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lab1.data.room.models.TariffEntity

@Dao
interface TariffDao {

    @Insert
    suspend fun save(vararg tariffs: TariffEntity)

    @Query("delete from TariffEntity where id = :id")
    suspend fun delete(id: String)

    @Query("select * from TariffEntity")
    suspend fun get() : List<TariffEntity>



}