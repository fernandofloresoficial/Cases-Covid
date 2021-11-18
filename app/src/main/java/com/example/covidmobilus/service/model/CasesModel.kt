package com.example.covidmobilus.service.model

import android.annotation.SuppressLint
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

class CasesModel {

    //TODO - adicionar deaths e mudar cases para confirmed

    @SerializedName("Country")
    var country: String = ""

    @SerializedName("Cases")
    var cases: Int = 0

    @SerializedName("Date")
    val date: String = ""

}