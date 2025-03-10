package com.jsm.cashierone

class WasmPlatform: Platform {
    override val name: String = PlatformNames.JS
}

actual fun getPlatform(): Platform = WasmPlatform()