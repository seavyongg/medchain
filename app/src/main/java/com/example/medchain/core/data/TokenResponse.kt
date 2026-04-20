package com.example.medchain.core.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val data: Data
) {
    data class Data(
        val user: User,
        val token: String,
    ){
        data class User(
            @SerialName("first_name")
            val firstName: String,
            @SerialName("last_name")
            val lastName: String,
            @SerialName("nid")
            val nid: String,
        )
    }
}
