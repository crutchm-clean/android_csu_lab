package com.example.lab1.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TariffEntity(
    val title: String,
    val desc: String,
    val cost: Int,
    @PrimaryKey val id: String,
)
