package com.github.ephelsa.loginpagedribble.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.github.ephelsa.loginpagedribble.R
import com.github.ephelsa.loginpagedribble.ui.component.LoginTextField
import com.github.ephelsa.loginpagedribble.viewmodel.LoginViewModel

typealias OnValueCallback<T> = (T) -> Unit
typealias OnCallback = () -> Unit

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val emailTextValue by viewModel.onEmailChange.collectAsState()
    val passwordTextValue by viewModel.onPasswordChange.collectAsState()
    val isLoginButtonDisabled by viewModel.onLoginButtonDisabled.collectAsState()
    val authState by viewModel.onAuthState.collectAsState()

    if (isLoginButtonDisabled) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(modifier = Modifier.size(60.dp))
        }
    }
    Column {
        // TODO: Add header
        // TODO: Sign in to continue
        if (!isLoginButtonDisabled) {
            FieldsContainer(
                onEmailChange = viewModel::onEmailTextChange,
                emailValue = emailTextValue ?: "",
                onPasswordChange = viewModel::onPasswordTextChange,
                passwordValue = passwordTextValue ?: ""
            )
            AuthState(authState)
        }
        // TODO: Password text field
        // TODO: Forgot your password
        // TODO: Sign in with Facebook button
        // TODO: Create new account
        // TODO: Login button
        LoginButtonContainer(isLoginButtonDisabled, viewModel::onLoginClick)
    }
}

@Composable
fun AuthState(onAuthState: LoginViewModel.AuthState) {
    val text: String
    val color: Color

    when(onAuthState) {
        LoginViewModel.AuthState.Success -> {
            text = "Success"
            color = Color.Green
        }
        LoginViewModel.AuthState.Init -> {
            text = ""
            color = Color.Gray
        }
        LoginViewModel.AuthState.Failure -> {
            text = "Failure"
            color = Color.Red
        }
    }



    Text(text = text, color = color)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun FieldsContainer(
    onEmailChange: OnValueCallback<String>,
    emailValue: String,
    onPasswordChange: OnValueCallback<String>,
    passwordValue: String,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(top = 40.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LoginTextField(
            modifier = Modifier.padding(bottom = 8.dp),
            hint = stringResource(id = R.string.loginScreen_emailHint),
            onValueChange = onEmailChange,
            value = emailValue,
        )
        LoginTextField(
            hint = "Password",
            textFieldType = KeyboardType.Password,
            onValueChange = onPasswordChange,
            value = passwordValue
        )
    }
}

@Composable
private fun LoginButtonContainer(isButtonDisabled: Boolean, onClick: OnCallback) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 20.dp),
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            shape = RoundedCornerShape(12.dp),
            onClick = onClick,
            enabled = !isButtonDisabled,
        ) {
            Text(text = "Login")
        }
    }
}
