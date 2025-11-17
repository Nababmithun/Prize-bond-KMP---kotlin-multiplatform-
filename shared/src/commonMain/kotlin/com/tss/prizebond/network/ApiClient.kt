package com.tss.prizebond.network

import io.ktor.client.HttpClient

expect fun createHttpClient(): HttpClient
