package com.example.lab1.data.repos

import com.example.lab1.data.network.retrofit.ApiProvider
import com.example.lab1.domain.models.UserInfo
import com.example.lab1.domain.repos.UserRepository

class UserInfoRepositoryImpl(private val api: ApiProvider) : UserRepository {
    override suspend fun fetchUserInfo(): UserInfo = api.getApi().getUserInfo()[0]
}