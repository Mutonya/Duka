package com.maestro.duka.di

import android.app.Application
import androidx.room.Room
import com.maestro.duka.data.local.DukaDatabase
import com.maestro.duka.data.local.ProductsDao
import com.maestro.duka.data.local.ProductsTypeConvertor
import com.maestro.duka.data.manager.LocalUserManagerImpl
import com.maestro.duka.data.remote.FakeStoreApi
import com.maestro.duka.data.repository.AuthRepositoryImpl
import com.maestro.duka.data.repository.BookMarkRepository
import com.maestro.duka.data.repository.BookMarkRepositoryImpl
import com.maestro.duka.data.repository.HomeRepositoryImpl
import com.maestro.duka.domain.manager.LocalUserManager
import com.maestro.duka.domain.repository.AuthRepository
import com.maestro.duka.domain.repository.HomeRepository
import com.maestro.duka.domain.usecases.AuthUseCases.AuthUseCase
import com.maestro.duka.domain.usecases.AuthUseCases.AuthUseCaseImpl
import com.maestro.duka.domain.usecases.AuthUseCases.LocalUserManagerUseCases
import com.maestro.duka.domain.usecases.BookMark.BookMarkUseCase
import com.maestro.duka.domain.usecases.BookMark.BookMarkUseCaseImpl
import com.maestro.duka.domain.usecases.HomeUseCases.HomeUseCaseImpl
import com.maestro.duka.domain.usecases.HomeUseCases.HomeUseCases
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module

object DukaModule {

    @Provides
    @Singleton
    fun provideOkHttp():OkHttpClient {

        val loggingInterceptor = HttpLoggingInterceptor{
        }.apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton

    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ):Retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }
    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): FakeStoreApi {
        return retrofit.create(FakeStoreApi::class.java)
    }

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ):LocalUserManager = LocalUserManagerImpl(application)
    @Provides
    @Singleton
    fun provideLocalManagerUseCase(
        localUserManager: LocalUserManager
    )= LocalUserManagerUseCases(localUserManager)

    @Provides
    @Singleton

    fun provideProductsDatabase(
        application: Application
    ):DukaDatabase{
        return Room.databaseBuilder(
            context = application,
            klass = DukaDatabase::class.java,
            name = "DukaDB"
        ).addTypeConverter(ProductsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }
    @Provides
    @Singleton

    fun provideDukaDao(
        dukaDatabase: DukaDatabase
    ):ProductsDao = dukaDatabase.productsDao


    @Module
    @InstallIn(SingletonComponent::class)

    interface AuthModule{
        @Binds
        @Singleton
        fun provideAuthRepository(authRepository: AuthRepositoryImpl):AuthRepository
        @Binds
        @Singleton
        fun providesAuthUseCase(authUseCase: AuthUseCase):AuthUseCaseImpl

    }

    @Module
    @InstallIn(SingletonComponent::class)

    interface HomeModule{
        @Binds
        @Singleton

        fun provideHomeRepository(homeRepositoryImpl: HomeRepositoryImpl):HomeRepository

        @Binds
        @Singleton
        fun provideHomeUseCases(homeUseCase:HomeUseCases):HomeUseCaseImpl


        @Binds
        @Singleton

        fun provideBookMarkRepository(bookMarkRepositoryImpl: BookMarkRepositoryImpl):BookMarkRepository

        @Binds
        @Singleton
        fun providebookmarkusecase(bookmarkUseCase:BookMarkUseCase):BookMarkUseCaseImpl
    }



}