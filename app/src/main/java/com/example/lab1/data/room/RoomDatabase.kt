package com.example.lab1.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.lab1.data.room.dao.BalanceDao
import com.example.lab1.data.room.dao.TariffDao
import com.example.lab1.data.room.dao.UserInfoDao
import com.example.lab1.data.room.models.BalanceEntity
import com.example.lab1.data.room.models.TariffEntity
import com.example.lab1.data.room.models.UserInfoEntity

@Database(
    entities = [
        TariffEntity::class,
        BalanceEntity::class,
        UserInfoEntity::class
    ], version = 1
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserInfoDao
    abstract fun getBalanceDao(): BalanceDao
    abstract fun getTariffDao(): TariffDao

}