package com.example.medchain.core.data

data class ConfirmInfo(
    val token: String,
)
data class ConfirmInfoRequest(
    val token: String,
)
fun ConfirmInfo.toConfirmInfoRequest(): ConfirmInfoRequest {
    return ConfirmInfoRequest(
        token = this.token,
    )
}

