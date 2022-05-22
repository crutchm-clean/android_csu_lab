package com.example.lab1.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BalanceEntity(
    val accNum: Int,
    val balance: Double,
    val nextPay: Double,
    @PrimaryKey val id: String,
)
