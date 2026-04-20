package com.example.medchain.core.network.services

import android.content.Context
import android.util.Log
import com.example.medchain.BuildConfig
import com.example.medchain.core.data.ClaimTokenRequest
import com.example.medchain.core.data.ProfileResponse
import com.example.medchain.core.network.interceptor.NetworkConnectionInterceptor
import com.example.medchain.core.network.interceptor.TokenInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("claim")
    suspend fun claimToken(
        @Body token: ClaimTokenRequest
    ): Response<ProfileResponse>

    companion object {
        val baseUrl = BuildConfig.BASE_URL_API
        operator fun invoke(
            context: Context ,
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ) : ApiService {
            val tokenInterceptor = TokenInterceptor(context)
            val okHttpClient = okhttp3.OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .addInterceptor(tokenInterceptor)
                .addInterceptor { chain ->
                    val request = chain.request()
                    Log.d("REQUEST", "URL: ${request.url}, Headers: ${request.headers}")
                    chain.proceed(request)
                }
                .build()
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}