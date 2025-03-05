package com.jsm.cashierone.utils

import okio.Buffer

fun String.sha2Hash(): String {
    val buffer = Buffer().writeUtf8(this)
    val sha256 = buffer.readByteString().sha256().hex()
    return sha256
}