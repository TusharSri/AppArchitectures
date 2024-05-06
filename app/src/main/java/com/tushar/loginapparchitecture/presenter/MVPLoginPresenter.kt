package com.tushar.loginapparchitecture.presenter

import com.tushar.loginapparchitecture.model.MVPLoginModel

class MVPLoginPresenter(private var view: MVPLoginContract.View) : MVPLoginContract.Presenter {
    override fun checkLoginCredentials(mvpLoginModel: MVPLoginModel) {
        if (mvpLoginModel.email == "admin@gmail.com" && mvpLoginModel.password == "admin")
            view.onSuccess("Success")
        else
            view.onError("Failure")
    }

}