package com.jsm.cashierone

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jsm.cashierone.ui.login.LoginScreen
import com.jsm.cashierone.ui.theme.OneTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(modifier: Modifier = Modifier) {
    OneTheme {
        LoginScreen(modifier)
    }
}