package com.example.medchain.core.network.di

import android.content.Context
import com.example.medchain.core.application.MySharedPreference
import com.example.medchain.core.network.interceptor.NetworkConnectionInterceptor
import com.example.medchain.core.network.services.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideNetworkConnectionInterceptor(
        @ApplicationContext context: Context
    ) : NetworkConnectionInterceptor = NetworkConnectionInterceptor(context)

    @Provides
    @Singleton
    fun provideApiNetwork(
        @ApplicationContext context: Context,
        interceptor: NetworkConnectionInterceptor
    ) : ApiService = ApiService(context , interceptor)

    @Provides
    @Singleton
    fun provideSharePreference(
        @ApplicationContext context: Context
    ) : MySharedPreference = MySharedPreference(context)
}