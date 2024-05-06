package com.tushar.loginapparchitecture.presenter

import com.tushar.loginapparchitecture.model.MVPLoginModel

interface MVPLoginContract {
    interface View {
        fun onSuccess(message: String)
        fun onError(message: String)

    }

    interface Presenter {
        fun checkLoginCredentials(mvpLoginModel: MVPLoginModel)
    }
}