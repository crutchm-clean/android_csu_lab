package com.example.lab1.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab1.domain.usecases.interfaces.GetBalanceUseCase
import com.example.lab1.domain.usecases.interfaces.GetTariffUseCase
import com.example.lab1.domain.usecases.interfaces.GetUserInfoUseCase

class ViewModelFactory(
    private val userInfoUseCase: GetUserInfoUseCase,
    private val tariffUseCase: GetTariffUseCase,
    private val balanceUseCase: GetBalanceUseCase
    ): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T  = com.example.lab1.presentation.viewModels.ViewModel(balanceUseCase, userInfoUseCase, tariffUseCase) as T

}