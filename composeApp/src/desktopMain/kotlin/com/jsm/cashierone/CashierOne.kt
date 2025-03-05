package com.jsm.cashierone

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(WindowPlacement.Maximized),
        alwaysOnTop = true,
        resizable = false,
        title = "CashierOne",
    ) {
        App(modifier = Modifier.fillMaxWidth(0.4f))
    }
}