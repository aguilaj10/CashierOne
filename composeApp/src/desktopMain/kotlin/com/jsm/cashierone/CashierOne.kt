package com.jsm.cashierone

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import cashierone.composeapp.generated.resources.Res
import cashierone.composeapp.generated.resources.desktop_menu_sales
import com.jsm.cashierone.ui.theme.MainContainer
import com.jsm.cashierone.ui.theme.WindowSize
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    val windowState = rememberWindowState(WindowPlacement.Maximized)
    val mainContainer = remember { JVMPlatform() }
    Window(
        onCloseRequest = ::exitApplication,
        state = windowState,
        alwaysOnTop = true,
        resizable = false,
        title = "CashierOne",
    ) {

        MenuBar {
            Menu(
                text = stringResource(Res.string.desktop_menu_sales),
                mnemonic = 'S',
            ) { }
        }

        App(
            modifier = Modifier.fillMaxWidth(0.4f),
            mainContainer = MainContainer(
                WindowSize.basedOnWidth(windowState.size.width),
                mainContainer
            ),
        )
    }
}