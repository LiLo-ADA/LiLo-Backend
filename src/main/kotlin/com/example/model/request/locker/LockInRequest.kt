package com.example.model.request.locker

import kotlinx.serialization.Serializable

@Serializable
data class LockInRequest(
    val id: String,
    val password: String? = null
)
