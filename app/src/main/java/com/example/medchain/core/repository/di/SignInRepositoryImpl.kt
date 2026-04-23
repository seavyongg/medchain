package com.example.medchain.core.repository.di

import com.example.medchain.core.data.ClaimTokenRequest
import com.example.medchain.core.data.ProfileResponse
import com.example.medchain.core.network.services.ApiService
import com.example.medchain.core.network.services.SafeApiRequest
import javax.inject.Inject

class SignInRepositoryImpl @Inject constructor(
    private val api: ApiService
) : SafeApiRequest(), SignInRepository {
    override suspend fun userSignIn(signInRequest : ClaimTokenRequest) : ProfileResponse {
        return apiRequest {
            api.claimToken(signInRequest)
        }
    }
}