package com.tss.prizebond

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform