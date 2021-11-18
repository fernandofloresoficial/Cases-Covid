package com.example.covidmobilus.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.covidmobilus.R
import com.example.covidmobilus.service.constants.CovidConstants
import com.example.covidmobilus.service.repository.local.SecurityPreferences
import com.example.covidmobilus.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        // Inicializa eventos
        setListeners()
        observer()

        verifyLoggedUser()
    }

    private fun setListeners() {
        button_login.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login) {
            if (edit_email.text.isEmpty()){
                Toast.makeText(this, "Informe o email", Toast.LENGTH_LONG).show()
            }else{
                handleLogin()
                startActivity(Intent(this, MainActivity::class.java))
            }

        }
    }

    private fun handleLogin() {
        val email = edit_email.text.toString()
        mViewModel.doLogin("", email)
    }

    private fun verifyLoggedUser() {
        mViewModel.verifyLoggedUser()
    }

    private fun observer() {

        mViewModel.loggedUser.observe(this, Observer {
            if (it){
                startActivity(Intent(this, MainActivity::class.java))
            }
        })
    }


}