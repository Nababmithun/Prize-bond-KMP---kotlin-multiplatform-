package com.tss.prizebond.di

import com.tss.prizebond.data.AuthRepository
import com.tss.prizebond.data.AuthViewModel
import com.tss.prizebond.network.AuthApi
import com.tss.prizebond.network.createHttpClient


object AuthFactory {
    fun createAuthViewModel(): AuthViewModel {
        val client = createHttpClient()
        val api = AuthApi(client)
        val repo = AuthRepository(api)
        return AuthViewModel(repo)
    }
}
