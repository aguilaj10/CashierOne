package com.jsm.cashierone.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
internal fun HSpacer(size: Size, modifier: Modifier = Modifier) {
    Spacer(modifier.width(size.dp))
}

@Composable
internal fun VSpacer(size: Size, modifier: Modifier = Modifier) {
    Spacer(modifier.height(size.dp))
}

internal val Size.dp: Dp
    get() = when(this) {
        Size.Small -> 8.dp
        Size.Medium -> 16.dp
        Size.Large -> 32.dp
    }


enum class Size {
    Small, Medium, Large
}