package com.example.lab1.domain.usecases.implementations

import com.example.lab1.domain.models.UserInfo
import com.example.lab1.domain.repos.UserRepository
import com.example.lab1.domain.usecases.interfaces.GetUserInfoUseCase

class GetUserInfoUseCaseImpl(private val repo : UserRepository) : GetUserInfoUseCase{
    override suspend fun getUserInfo(): UserInfo = repo.fetchUserInfo()
}