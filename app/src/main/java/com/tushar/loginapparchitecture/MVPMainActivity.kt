package com.tushar.loginapparchitecture

import android.annotation.SuppressLint
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tushar.loginapparchitecture.model.MVPLoginModel
import com.tushar.loginapparchitecture.presenter.MVPLoginContract
import com.tushar.loginapparchitecture.presenter.MVPLoginPresenter
import com.tushar.loginapparchitecture.ui.theme.LoginAppArchitectureTheme

class MVPMainActivity : ComponentActivity(), MVPLoginContract.View {

    private lateinit var presenter: MVPLoginContract.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginAppArchitectureTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MVPScreen()
                }
            }
        }
    }

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @Composable
    private fun MVPScreen() {
        presenter = MVPLoginPresenter(this)
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
                    val mvpLoginModel = MVPLoginModel(email, password)
                    presenter.checkLoginCredentials(mvpLoginModel)
                }
            ) {
                Text("Login")
            }
        }
    }

    @Preview
    @Composable
    fun MVPScreenPreview() {
        MVPScreen()
    }

    override fun onSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}


