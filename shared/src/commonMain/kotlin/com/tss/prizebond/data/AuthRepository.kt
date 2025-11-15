package com.tss.prizebond.data

import com.tss.prizebond.models.User
import com.tss.prizebond.network.AuthApi


class AuthRepository(
    private val api: AuthApi
) {
    suspend fun login(email: String, password: String): User {
        return api.login(email, password).user
    }

    suspend fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): User {
        val res = api.register(name, email, password, passwordConfirmation)
        return User(
            id = res.id,
            name = res.name,
            email = res.email,
            createdAt = res.created_at,
            updatedAt = res.updated_at
        )
    }
}