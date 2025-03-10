package com.jsm.cashierone

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.jsm.cashierone.ui.login.LoginScreen
import com.jsm.cashierone.ui.theme.LocalMainContainer
import com.jsm.cashierone.ui.theme.MainContainer
import com.jsm.cashierone.ui.theme.OneTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(modifier: Modifier = Modifier, mainContainer: MainContainer) {
    OneTheme {
        Surface(modifier = Modifier.background(MaterialTheme.colorScheme.background)) {
            CompositionLocalProvider(LocalMainContainer provides mainContainer) {
                LoginScreen(modifier)
            }
        }
    }
}