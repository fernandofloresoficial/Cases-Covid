package com.example.covidmobilus.service.model

import com.google.gson.annotations.SerializedName

data class AllDataModel (

    @SerializedName("Country")
    var country: String = "",

    @SerializedName("Confirmed")
    var confirmed: Int = 0,

    @SerializedName("Deaths")
    var deaths: Int = 0,

    @SerializedName("Date")
    val date: String = ""

)