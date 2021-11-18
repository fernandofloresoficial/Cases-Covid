package com.example.covidmobilus.service.repository

import android.content.Context
import com.example.covidmobilus.R
import com.example.covidmobilus.service.constants.CovidConstants
import com.example.covidmobilus.service.listeners.APIListener
import com.example.covidmobilus.service.model.AllDataModel
import com.example.covidmobilus.service.repository.remote.AllDataService
import com.example.covidmobilus.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllDataRepository(val context: Context) {

    val mRemote = RetrofitClient.createService(AllDataService::class.java)

    fun listAllData(listener: APIListener<AllDataModel>) {
        val call: Call<List<AllDataModel>> = mRemote.listAllData()

        call.enqueue(object : Callback<List<AllDataModel>> {

            override fun onFailure(call: Call<List<AllDataModel>>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

            override fun onResponse(
                call: Call<List<AllDataModel>>,
                response: Response<List<AllDataModel>>
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