package com.example.lab1.data.room.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PaymentEntity(@PrimaryKey val id: String, val total: Double) {
}