package com.jsm.cashierone.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun OneInputText(
    text: String,
    label: String,
    maxLines: Int = 1,
    type: Type = Type.NORMAL,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    Column {
        OutlinedTextField(
            value = text,
            onValueChange = { onTextChange(it) },
            modifier = Modifier.then(modifier),
            label = { Text(label) },
            singleLine = maxLines == 1,
            maxLines = maxLines,
            visualTransformation =
            if (type == Type.SECURE) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
        )
    }
}

enum class Type {
    NORMAL,
    SECURE,
}