package com.example.medchain.core.data

import kotlinx.serialization.SerialName

data class DoctorRequest(
    @SerialName("qr_token")
    val qrCode: String? = null ,
    @SerialName("user_token")
    val userToken: String? = null,
    @SerialName("approved")
    val approved: Int = 1 ,
)

