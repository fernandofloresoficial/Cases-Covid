package com.example.covidmobilus.service.repository.remote

import android.text.format.DateFormat
import com.example.covidmobilus.service.model.CasesModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.time.format.DateTimeFormatter

interface CasesService {

    @GET("country/brazil/status/confirmed?")
    fun listCases(@Query("from") from: String, @Query("to") to: String) : Call<List<CasesModel>>

}