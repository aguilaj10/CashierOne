package com.jsm.cashierone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jsm.cashierone.ui.theme.MainContainer
import com.jsm.cashierone.ui.theme.WindowSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(mainContainer = MainContainer(WindowSize.COMPACT, AndroidPlatform()))
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(mainContainer = MainContainer(WindowSize.COMPACT, AndroidPlatform()))
}