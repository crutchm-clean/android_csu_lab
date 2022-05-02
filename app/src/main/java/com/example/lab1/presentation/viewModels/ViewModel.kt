package com.example.lab1.presentation.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lab1.domain.models.Balance
import com.example.lab1.domain.models.Tariff
import com.example.lab1.domain.models.UserInfo
import com.example.lab1.domain.usecases.interfaces.GetBalanceUseCase
import com.example.lab1.domain.usecases.interfaces.GetTariffUseCase
import com.example.lab1.domain.usecases.interfaces.GetUserInfoUseCase
import kotlinx.coroutines.launch

class ViewModel(
    private val balanceUseCase: GetBalanceUseCase,
    private val userInfoUseCase: GetUserInfoUseCase,
    private val tariffUseCase: GetTariffUseCase
) : AbstractViewModel(){
    override val balance = MutableLiveData<Balance>()
    override val userInfo = MutableLiveData<UserInfo>()
    override val loading = MutableLiveData(false)
    override val tariffs = MutableLiveData<List<Tariff>>()

    override fun refreshData() {
        viewModelScope.launch {
            loading.value = true
            balance.value = balanceUseCase.getBalance()
            tariffs.value = tariffUseCase.getTariff()
            userInfo.value = userInfoUseCase.getUserInfo()
            loading.value = false
        }
    }
}