package com.example.covidmobilus.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.covidmobilus.service.constants.CovidConstants
import com.example.covidmobilus.service.repository.local.SecurityPreferences

class LoginViewModel (application: Application) : AndroidViewModel(application){

    private val mSharedPreferences = SecurityPreferences(application)
    private val mLoggedUser = MutableLiveData<Boolean>()
    var loggedUser: LiveData<Boolean> = mLoggedUser

    fun doLogin(key: String, email: String) {
        mSharedPreferences.store(CovidConstants.SHARED.KEY, key)
        mSharedPreferences.store(CovidConstants.SHARED.EMAIL, email)
    }

    fun verifyLoggedUser() {

        val email = mSharedPreferences.get(CovidConstants.SHARED.EMAIL)

        val logged = ( email != "")
        mLoggedUser.value = logged

    }
}