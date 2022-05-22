package com.example.lab1.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserInfoEntity(
    val firstName: String,
    val lastName: String,
    val address: String,
    @PrimaryKey val id: String,
)
