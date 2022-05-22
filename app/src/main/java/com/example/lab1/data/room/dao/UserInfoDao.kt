package com.example.lab1.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.lab1.data.room.models.UserInfoEntity

@Dao
interface UserInfoDao {

    @Insert
    suspend fun save(vararg users: UserInfoEntity)

    @Query("select * from UserInfoEntity")
    suspend fun get() : List<UserInfoEntity>
}