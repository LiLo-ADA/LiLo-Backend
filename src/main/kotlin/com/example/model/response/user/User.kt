package com.example.model.response.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val session: Int
)
