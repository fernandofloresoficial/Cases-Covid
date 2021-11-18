package com.example.covidmobilus.service.repository

import android.content.Context
import com.example.covidmobilus.R
import com.example.covidmobilus.service.constants.CovidConstants
import com.example.covidmobilus.service.listeners.APIListener
import com.example.covidmobilus.service.model.CasesModel
import com.example.covidmobilus.service.repository.remote.CasesService
import com.example.covidmobilus.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CasesRepository(val context: Context) {

    val mRemote = RetrofitClient.createService(CasesService::class.java)

    fun listCases(from: String, to: String, listener: APIListener<CasesModel>) {
        val call: Call<List<CasesModel>> = mRemote.listCases(from, to)

        call.enqueue(object : Callback<List<CasesModel>> {

            override fun onFailure(call: Call<List<CasesModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<List<CasesModel>>,
                response: Response<List<CasesModel>>
            ) {

                if (response.code() != CovidConstants.HTTP.SUCCESS) {
                    val validation =
                        Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it)}
                }
            }

        })
    }
}