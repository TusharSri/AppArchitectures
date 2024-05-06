package com.tushar.loginapparchitecture

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tushar.loginapparchitecture.controller.MVCLoginController
import com.tushar.loginapparchitecture.ui.theme.LoginAppArchitectureTheme
import kotlinx.coroutines.launch

class MVCMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginAppArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MVCScreen()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    private fun MVCScreen() {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                lateinit var mvcLoginController: MVCLoginController

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
                        mvcLoginController = MVCLoginController(email, password)
                        val loginStatus = mvcLoginController.checkLoginCredentials()
                        scope.launch {
                            if (loginStatus)
                                snackbarHostState.showSnackbar("Successful login")
                            else
                                snackbarHostState.showSnackbar("Login Failed")
                        }
                    }
                ) {
                    Text("Login")
                }
            }
        }
    }

    @Preview
    @Composable
    fun MVCScreenPreview() {
        MVCScreen()
    }
}


