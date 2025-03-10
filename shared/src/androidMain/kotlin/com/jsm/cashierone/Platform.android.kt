package com.jsm.cashierone

class AndroidPlatform : Platform {
    override val name: String = PlatformNames.ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()