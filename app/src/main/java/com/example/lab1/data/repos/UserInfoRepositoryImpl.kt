package com.example.lab1.data.repos

import com.example.lab1.data.network.retrofit.ApiProvider
import com.example.lab1.data.room.dao.UserInfoDao
import com.example.lab1.data.room.models.BalanceEntity
import com.example.lab1.data.room.models.UserInfoEntity
import com.example.lab1.domain.models.Balance
import com.example.lab1.domain.models.UserInfo
import com.example.lab1.domain.repos.UserRepository

class UserInfoRepositoryImpl(private val api: ApiProvider, private val dao: UserInfoDao) : UserRepository {
    override suspend fun fetchUserInfo(): UserInfo {
        val db = dao.get()
        return if(db.isNotEmpty()){
            val user = db[0]
            UserInfo(user.firstName, user.lastName, user.address, user.id)
        } else{
            val cloud = api.getApi().getUserInfo()[0]
            dao.save( UserInfoEntity(cloud.firstName, cloud.lastName, cloud.address, cloud.id))
            cloud
        }
    }
}