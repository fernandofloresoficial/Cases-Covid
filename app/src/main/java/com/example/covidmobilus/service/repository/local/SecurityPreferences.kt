package com.example.covidmobilus.service.repository.local

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences (context: Context) {

    private val mPreferences: SharedPreferences =
        context.getSharedPreferences("covidShared", Context.MODE_PRIVATE)

    fun store(key: String, email: String) {
        mPreferences.edit().putString(key, email).apply()
    }

    fun get(key: String): String {
        return mPreferences.getString(key, "") ?: ""
    }

}