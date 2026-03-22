package com.example.medchain.core.network.interceptor

import android.content.Context
import coil.request.ImageResult
import com.example.medchain.core.application.MySharedPreference
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(
    context: Context
): Interceptor {

    private var token : String? = null
    private var sharedPreferences = MySharedPreference(context)
    override fun intercept(chain: Interceptor.Chain): Response {
        token = sharedPreferences.getToken()
        val originalRequest = chain.request()
        val builder = originalRequest.newBuilder()
        if(!token.isNullOrEmpty()){
            builder.header("Authorization", "Bearer $token")

        }
        val modifiedRequest = builder.build()
        return chain.proceed(modifiedRequest)
    }

}