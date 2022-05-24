package com.example.lab1.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.lab1.domain.models.Balance
import com.example.lab1.domain.models.Tariff
import com.example.lab1.domain.models.UserInfo

abstract class AbstractViewModel : ViewModel() {
    abstract val balance: LiveData<Balance>
    abstract val tariffs : LiveData<List<Tariff>>
    abstract val userInfo : LiveData<UserInfo>
    abstract val loading: LiveData<Boolean>

    abstract fun refreshData()
    abstract fun delete(id: String)
}