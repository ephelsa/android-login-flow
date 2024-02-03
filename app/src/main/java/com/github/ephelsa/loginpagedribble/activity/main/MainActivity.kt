package com.github.ephelsa.loginpagedribble.activity.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.viewModelFactory
import com.github.ephelsa.loginpagedribble.data.AuthUserDataInMemory
import com.github.ephelsa.loginpagedribble.repository.AuthUserRepository
import com.github.ephelsa.loginpagedribble.repository.AuthUserRepositoryImpl
import com.github.ephelsa.loginpagedribble.ui.screen.login.LoginScreen
import com.github.ephelsa.loginpagedribble.ui.theme.LoginPageDribbleTheme
import com.github.ephelsa.loginpagedribble.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {

    private val loginViewModel by viewModels<LoginViewModel> {
        viewModelFactory {
            addInitializer(LoginViewModel::class) {
                LoginViewModel(
                    authUserRepository = AuthUserRepositoryImpl(
                        authUserData = AuthUserDataInMemory()
                    )
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginPageDribbleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(viewModel = loginViewModel)
                }
            }
        }
    }
}
