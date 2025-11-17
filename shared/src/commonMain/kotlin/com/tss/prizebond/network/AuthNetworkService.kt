package com.tss.prizebond.network

import com.tss.prizebond.models.LoginResponse
import com.tss.prizebond.models.RegisterResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.forms.submitForm
import io.ktor.http.Parameters

class AuthNetworkService(
    private val client: HttpClient
) {

    suspend fun login(email: String, password: String): LoginResponse {
        return client.submitForm(
            url = ApiRoutes.LOGIN,
            formParameters = Parameters.build {
                append("email", email)
                append("password", password)
            }
        ).body()
    }

    suspend fun register(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String
    ): RegisterResponse {
        return client.submitForm(
            url = ApiRoutes.REGISTER,
            formParameters = Parameters.build {
                append("name", name)
                append("email", email)
                append("password", password)
                append("password_confirmation", passwordConfirmation)
            }
        ).body()
    }
}
