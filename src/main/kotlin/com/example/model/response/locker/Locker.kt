package com.example.model.response.locker

import com.example.model.response.user.User
import kotlinx.serialization.Serializable

@Serializable
data class Locker(
    val id: String,
    val area: String,
    val number: Int,
    val status: Int,
    val reportCount: Int,
    val user: User
)