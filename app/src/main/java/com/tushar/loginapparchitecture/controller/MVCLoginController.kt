package com.tushar.loginapparchitecture.controller

import com.tushar.loginapparchitecture.model.MVCLoginModel

class MVCLoginController (var email: String, var password: String){
    lateinit var mvcLoginModel: MVCLoginModel

    fun checkLoginCredentials(): Boolean {
        mvcLoginModel = MVCLoginModel(email, password)
        return mvcLoginModel.email == "admin@gmail.com" && mvcLoginModel.password == "admin"
    }

}