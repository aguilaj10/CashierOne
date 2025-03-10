package com.jsm.cashierone

class JVMPlatform: Platform {
    override val name: String = PlatformNames.JVM
}

actual fun getPlatform(): Platform = JVMPlatform()