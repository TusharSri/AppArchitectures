package com.tushar.loginapparchitecture.viewmodel

import androidx.lifecycle.ViewModel
import com.tushar.loginapparchitecture.model.MVVMLoginModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MVVMLoginViewModel : ViewModel() {
    private val _credentials = MutableStateFlow(MVVMLoginModel("", ""))
    val credentials: StateFlow<MVVMLoginModel> = _credentials

    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult

    fun checkLoginCredentials() {
        val credentials = credentials.value
        _loginResult.value = credentials.email == "admin@gmail.com" && credentials.password == "admin"
    }
}