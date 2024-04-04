package com.example.model.response.locker

import kotlinx.serialization.Serializable

@Serializable
data class Locker(
    val id: String,
    val area: String,
    val number: Int,
    val status: Int,
    val reported: Boolean,
    val password: String?
)