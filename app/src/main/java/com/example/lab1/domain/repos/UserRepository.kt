package com.example.lab1.domain.repos

import com.example.lab1.domain.models.Tariff
import com.example.lab1.domain.models.UserInfo

interface UserRepository {
    suspend fun fetchUserInfo() : UserInfo
}