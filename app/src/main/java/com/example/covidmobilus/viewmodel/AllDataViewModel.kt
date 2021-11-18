package com.example.covidmobilus.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.covidmobilus.service.listeners.APIListener
import com.example.covidmobilus.service.model.AllDataModel
import com.example.covidmobilus.service.repository.AllDataRepository

class AllDataViewModel (application: Application) : AndroidViewModel(application) {

    private val mAllDataRepository = AllDataRepository(application)

    private val mList = MutableLiveData<List<AllDataModel>>()
    var list: LiveData<List<AllDataModel>> = mList

    fun listAllData() {
        mAllDataRepository.listAllData(object : APIListener<AllDataModel> {
            override fun onSuccess(model: List<AllDataModel>) {
                mList.value = model.reversed()
            }

            override fun onFailure(str: String) {
                mList.value = arrayListOf()
            }
        })
    }

    fun findData(): MutableList<AllDataModel> {

        val mData: MutableList<AllDataModel> = mutableListOf()

        mList.value?.forEachIndexed { index, allDataModel ->

            //TODO - Implementar a logica pra somar o numero de casos e mortes agrupando por data

            //TODO - Implementar a logica pra calcular o numero de casos e de morte por data


            val confirmedYesterday = mList.value?.getOrNull(index + 1)?.confirmed ?: 0
            val confirmedToday = allDataModel.confirmed - confirmedYesterday

            val deathsYesterday = mList.value?.getOrNull(index + 1)?.deaths ?: 0
            val deathsToday = allDataModel.deaths - deathsYesterday

            mData.add(
                AllDataModel(
                    date = allDataModel.date,
                    confirmed = confirmedToday,
                    deaths = deathsToday
                )
            )

        }

        val maxDeath = mData.maxWithOrNull(Comparator.comparingInt {it.confirmed })
        println("Maior numero de mortes : $maxDeath")

        return mData
    }
}