package com.example.batmanmoviesapp.di

import com.example.batmanmoviesapp.data.remote.NetworkService
import com.example.batmanmoviesapp.util.AppConstant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(gsonConverterFactory: GsonConverterFactory) : NetworkService {
        return Retrofit
            .Builder()
            .baseUrl(AppConstant.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }
}