package com.tss.prizebond.di

import com.tss.prizebond.data.AuthRepository
import com.tss.prizebond.data.AuthViewModel
import com.tss.prizebond.network.AuthNetworkService
import com.tss.prizebond.network.createHttpClient


object AuthFactory {
    fun createAuthViewModel(): AuthViewModel {
        val client = createHttpClient()
        val api = AuthNetworkService(client)
        val repo = AuthRepository(api)
        return AuthViewModel(repo)
    }
}
