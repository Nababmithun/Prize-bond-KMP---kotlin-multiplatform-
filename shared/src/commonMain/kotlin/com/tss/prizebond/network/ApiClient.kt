package com.tss.prizebond.network

import io.ktor.client.HttpClient

expect fun createHttpClient(): HttpClient

const val BASE_URL = "https://heard-api.peopleplusbd.com/api"