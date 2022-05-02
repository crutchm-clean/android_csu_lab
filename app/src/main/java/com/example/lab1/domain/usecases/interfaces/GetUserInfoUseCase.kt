package com.example.lab1.domain.usecases.interfaces

import com.example.lab1.domain.models.UserInfo

interface GetUserInfoUseCase {
    suspend fun getUserInfo() : UserInfo
}