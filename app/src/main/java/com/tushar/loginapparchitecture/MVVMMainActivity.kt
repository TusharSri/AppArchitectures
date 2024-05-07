package com.tushar.loginapparchitecture

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.tushar.loginapparchitecture.viewmodel.MVVMLoginViewModel
import com.tushar.loginapparchitecture.ui.theme.LoginAppArchitectureTheme

class MVVMMainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MVVMLoginViewModel::class.java]

        setContent {
            LoginAppArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MVVMScreen(viewModel)
                }
            }
        }
    }

    @Composable
    private fun MVVMScreen(viewModel: MVVMLoginViewModel) {
        val loginResult = viewModel.loginResult.collectAsState()
        when (loginResult.value) {
            true -> {
                Toast.makeText(baseContext, "Login Successful", Toast.LENGTH_LONG).show()
            }
            false -> {
                Toast.makeText(baseContext, "Failed", Toast.LENGTH_LONG).show()
            }
            else -> {
                //Do Nothing here
            }
        }

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            TextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = { Text("Email id") }
            )

            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = { Text("Enter Password") }
            )

            TextButton(
                onClick = {
                    viewModel.credentials.value.email = email
                    viewModel.credentials.value.password = password
                    viewModel.checkLoginCredentials()
                }
            ) {
                Text("Login")
            }
        }
    }
}


