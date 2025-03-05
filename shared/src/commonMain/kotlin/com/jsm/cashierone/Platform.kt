package com.jsm.cashierone

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform