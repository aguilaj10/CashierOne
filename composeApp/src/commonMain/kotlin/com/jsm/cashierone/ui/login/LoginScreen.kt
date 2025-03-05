package com.jsm.cashierone.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cashierone.composeapp.generated.resources.Res
import cashierone.composeapp.generated.resources.app_name
import cashierone.composeapp.generated.resources.login_button
import cashierone.composeapp.generated.resources.login_email
import cashierone.composeapp.generated.resources.login_password
import cashierone.composeapp.generated.resources.login_title
import cashierone.composeapp.generated.resources.svg_app_logo
import com.jsm.cashierone.di.loginModule
import com.jsm.cashierone.features.login.LoginEvent
import com.jsm.cashierone.features.login.LoginViewModel
import com.jsm.cashierone.features.login.getEmail
import com.jsm.cashierone.features.login.getPassword
import com.jsm.cashierone.ui.components.OneButton
import com.jsm.cashierone.ui.components.OneInputText
import com.jsm.cashierone.ui.components.Size
import com.jsm.cashierone.ui.components.Style
import com.jsm.cashierone.ui.components.Type
import com.jsm.cashierone.ui.components.VSpacer
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.kodein.di.compose.rememberInstance
import org.kodein.di.compose.withDI

@Composable
fun LoginScreen(modifier: Modifier = Modifier) = withDI(loginModule) {
    val viewModel by rememberInstance<LoginViewModel>()
    val state = viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Card(modifier = modifier.padding(horizontal = 24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    imageVector = vectorResource(Res.drawable.svg_app_logo),
                    contentDescription = "App Logo",
                    modifier = Modifier.size(80.dp),
                )
                Text(
                    text = stringResource(Res.string.app_name),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    color = MaterialTheme.colorScheme.tertiary,
                )
            }
        }
        VSpacer(Size.Medium)
        Card(modifier = modifier.padding(horizontal = 24.dp)) {
            Column {
                Text(
                    text = stringResource(Res.string.login_title),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                )
                VSpacer(Size.Medium)
                OneInputText(
                    label = stringResource(Res.string.login_email),
                    text = state.value.getEmail(),
                    onTextChange = { viewModel.onEvent(LoginEvent.OnEmailChange(it)) },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                )
                VSpacer(Size.Small)
                OneInputText(
                    label = stringResource(Res.string.login_password),
                    text = state.value.getPassword(),
                    onTextChange = { viewModel.onEvent(LoginEvent.OnPasswordChange(it)) },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                    type = Type.SECURE,
                )
                VSpacer(Size.Small)
                OneButton(
                    style = Style.FILLED,
                    text = stringResource(Res.string.login_button),
                    onClick = { viewModel.onEvent(LoginEvent.OnLoginClick) },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                )
                VSpacer(Size.Small)
            }
        }
    }
}