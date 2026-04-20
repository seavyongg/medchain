package com.example.medchain.core.repository.di

import com.example.medchain.core.data.ClaimTokenRequest
import com.example.medchain.core.data.ProfileResponse

interface SignInRepository{
    suspend fun userSignIn( signInRequest : ClaimTokenRequest) : ProfileResponse
}