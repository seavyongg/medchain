package com.example.medchain.core.data

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileResponse(
    val data: Data
) {
    data class Data(
        val user: User,
        val token: String?= null,
    ){
        data class User(
            @SerializedName("first_name")
            val firstName: String ?= null,
            @SerializedName("last_name")
            val lastName: String ?= null,
            @SerializedName("nid")
            val nid: String ?= null,
        )
    }
}
