package com.jsm.cashierone

class IOSPlatform: Platform {
    override val name: String = PlatformNames.IOS
}

actual fun getPlatform(): Platform = IOSPlatform()