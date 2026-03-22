package com.example.medchain.core.data

import kotlinx.serialization.SerialName

data class ClaimTokenRequest(
    @SerialName("claim_token")
    val claimToken: String? = null
)
