package com.tss.prizebond.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val user: User
)

@Serializable
data class RegisterResponse(
    val id: Int,
    val name: String,
    val email: String,
    val created_at: String,
    val updated_at: String? = null
)