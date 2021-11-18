package com.example.covidmobilus.service.repository.remote

import com.example.covidmobilus.service.model.AllDataModel
import com.example.covidmobilus.service.model.CasesModel
import retrofit2.Call
import retrofit2.http.GET

interface AllDataService {

    @GET("live/country/brazil/status/confirmed/date/2021-10-01T13:13:30Z")
    fun listAllData() : Call<List<AllDataModel>>

}