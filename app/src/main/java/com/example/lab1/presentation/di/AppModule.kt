package com.example.lab1.presentation.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.lab1.data.network.retrofit.Api
import com.example.lab1.data.network.retrofit.ApiProvider
import com.example.lab1.data.network.retrofit.RetrofitClient
import com.example.lab1.data.repos.BalanceRepositoryImpl
import com.example.lab1.data.repos.TariffRepositoryImpl
import com.example.lab1.data.repos.UserInfoRepositoryImpl
import com.example.lab1.data.room.Migration1
import com.example.lab1.data.room.RoomDatabase
import com.example.lab1.data.room.dao.BalanceDao
import com.example.lab1.data.room.dao.TariffDao
import com.example.lab1.data.room.dao.UserInfoDao
import com.example.lab1.domain.repos.BalanceRepository
import com.example.lab1.domain.repos.TariffRepository
import com.example.lab1.domain.repos.UserRepository
import com.example.lab1.domain.usecases.implementations.DeleteTariffUseCaseImpl
import com.example.lab1.domain.usecases.implementations.GetBalanceUseCaseImpl
import com.example.lab1.domain.usecases.implementations.GetTariffUseCaseImpl
import com.example.lab1.domain.usecases.implementations.GetUserInfoUseCaseImpl
import com.example.lab1.domain.usecases.interfaces.DeleteTariffUseCase
import com.example.lab1.domain.usecases.interfaces.GetBalanceUseCase
import com.example.lab1.domain.usecases.interfaces.GetTariffUseCase
import com.example.lab1.domain.usecases.interfaces.GetUserInfoUseCase
import com.example.lab1.presentation.viewModels.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val context: Context) {
    //provide context
    @Provides
    fun provideContext() = context

    //provide db
    @Provides
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, RoomDatabase::class.java, "db").addMigrations(Migration1).build()


    //obviously provide vm factory
    @Provides
    fun provideViewModelFactory(
        userInfoUseCase: GetUserInfoUseCase,
        tariffUseCase: GetTariffUseCase,
        balanceUseCase: GetBalanceUseCase,
        deleteTariffUseCase: DeleteTariffUseCase
    ) = ViewModelFactory(userInfoUseCase, tariffUseCase, balanceUseCase, deleteTariffUseCase)

    //provide repos
    @Provides
    fun provideUserInfoRepo(apiProvider: ApiProvider, dao: UserInfoDao): UserRepository =
        UserInfoRepositoryImpl(apiProvider, dao)

    @Provides
    fun provideBalanceRepo(apiProvider: ApiProvider, dao: BalanceDao): BalanceRepository =
        BalanceRepositoryImpl(apiProvider, dao)

    @Provides
    fun provideTariffRepo(apiProvider: ApiProvider, dao: TariffDao): TariffRepository =
        TariffRepositoryImpl(apiProvider, dao)


    //provide dao
    @Provides
    fun provideBalanceDao(database: RoomDatabase) = database.getBalanceDao()

    @Provides
    fun providePaymentDao(database: RoomDatabase) = database.getPaymentDao()


    @Provides
    fun provideTariffDao(database: RoomDatabase) = database.getTariffDao()

    @Provides
    fun provideUserDao(database: RoomDatabase) = database.getUserDao()


    //provide use cases
    @Provides
    fun provideBalanceUseCase(repo: BalanceRepository): GetBalanceUseCase =
        GetBalanceUseCaseImpl(repo)

    @Provides
    fun provideTariffUseCase(repo: TariffRepository): GetTariffUseCase = GetTariffUseCaseImpl(repo)

    @Provides
    fun provideUserInfoUseCase(repo: UserRepository): GetUserInfoUseCase =
        GetUserInfoUseCaseImpl(repo)

    @Provides
    fun provideDeleteTariffUseCase(repo: TariffRepository): DeleteTariffUseCase =
        DeleteTariffUseCaseImpl(repo)

    //provide retrofit
    @Provides
    fun provideRetrofitClient(): RetrofitClient = RetrofitClient()

    @Provides
    fun provideApiProvider(client: RetrofitClient): ApiProvider = ApiProvider(client)

    @Provides
    fun provideApi(apiProvider: ApiProvider): Api = apiProvider.getApi()


}