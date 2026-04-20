package com.example.medchain.core.data

import kotlinx.serialization.SerialName

data class DoctorResponse(
    val data: Data
){
    data class Data(
        @SerialName("user_id")
        val userId: Int,
        @SerialName("qr_token")
        val qrToken: String,
        @SerialName("approved_at")
        val approvedAt: String
    )
}