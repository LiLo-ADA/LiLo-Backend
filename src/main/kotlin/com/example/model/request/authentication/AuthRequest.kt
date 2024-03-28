package com.example.model.request.authentication

import kotlinx.serialization.Serializable

@Serializable
data class AuthRequest(
    val email: String,
    val session: Int
)
