package com.example.medchain.core.data

data class ProfileResponse(
    val success: Boolean,
    val token: String,
    val data: Data
) {
    data class Data(
        val id: String,
        val email: String,
        val name: String,
        val profile: String
    )
}
