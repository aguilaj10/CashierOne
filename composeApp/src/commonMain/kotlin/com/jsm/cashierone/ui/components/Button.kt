package com.jsm.cashierone.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun OneButton(
    style: Style,
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (style) {
        Style.FILLED -> {
            Button(modifier = modifier, onClick = onClick) {
                Text(text = text)
            }
        }
        Style.OUTLINED -> {
            OutlinedButton(modifier = modifier, onClick = onClick) {
                Text(text = text)
            }
        }
        Style.TEXT -> {
            TextButton(modifier = modifier, onClick = onClick) {
                Text(text = text)
            }
        }
        Style.FLOATING ->
            ExtendedFloatingActionButton(
                modifier = modifier,
                onClick = onClick,
            ) {
                Text(text = text)
            }
    }
}

enum class Style {
    FILLED,
    OUTLINED,
    TEXT,
    FLOATING,
}