package com.example.medchain.core.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class ClaimTokenRequest(
    @SerializedName("claim_token")
    val claimToken: String? = null
)
