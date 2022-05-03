package com.example.lab1.presentation.di

import com.example.lab1.data.network.retrofit.Api
import com.example.lab1.data.network.retrofit.ApiProvider
import com.example.lab1.data.network.retrofit.RetrofitClient
import com.example.lab1.data.repos.BalanceRepositoryImpl
import com.example.lab1.data.repos.TariffRepositoryImpl
import com.example.lab1.data.repos.UserInfoRepositoryImpl
import com.example.lab1.domain.repos.BalanceRepository
import com.example.lab1.domain.repos.TariffRepository
import com.example.lab1.domain.repos.UserRepository
import com.example.lab1.domain.usecases.implementations.GetBalanceUseCaseImpl
import com.example.lab1.domain.usecases.implementations.GetTariffUseCaseImpl
import com.example.lab1.domain.usecases.implementations.GetUserInfoUseCaseImpl
import com.example.lab1.domain.usecases.interfaces.GetBalanceUseCase
import com.example.lab1.domain.usecases.interfaces.GetTariffUseCase
import com.example.lab1.domain.usecases.interfaces.GetUserInfoUseCase
import com.example.lab1.presentation.viewModels.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {
//obviously provide vm factory
    @Provides
    fun provideViewModelFactory(
        userInfoUseCase: GetUserInfoUseCase,
        tariffUseCase: GetTariffUseCase,
        balanceUseCase: GetBalanceUseCase
    ) = ViewModelFactory(userInfoUseCase, tariffUseCase, balanceUseCase)

//provide repos
    @Provides
    fun provideUserInfoRepo(apiProvider: ApiProvider) : UserRepository = UserInfoRepositoryImpl(apiProvider)

    @Provides
    fun provideBalanceRepo(apiProvider: ApiProvider) : BalanceRepository = BalanceRepositoryImpl(apiProvider)

    @Provides
    fun provideTariffRepo(apiProvider: ApiProvider) : TariffRepository = TariffRepositoryImpl(apiProvider)


//provide use cases
    @Provides
    fun provideBalanceUseCase(repo: BalanceRepository) : GetBalanceUseCase = GetBalanceUseCaseImpl(repo)

    @Provides
    fun provideTariffUseCase(repo: TariffRepository) : GetTariffUseCase = GetTariffUseCaseImpl(repo)

    @Provides
    fun provideUserInfoUseCase(repo: UserRepository) : GetUserInfoUseCase = GetUserInfoUseCaseImpl(repo)

//provide retrofit
    @Provides
    fun provideRetrofitClient() : RetrofitClient = RetrofitClient()

    @Provides
    fun provideApiProvider(client: RetrofitClient) : ApiProvider = ApiProvider(client)

    @Provides
    fun provideApi(apiProvider: ApiProvider) :Api = apiProvider.getApi()
}