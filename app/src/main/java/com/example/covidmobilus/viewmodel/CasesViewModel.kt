package com.example.covidmobilus.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.covidmobilus.service.listeners.APIListener
import com.example.covidmobilus.service.model.CasesModel
import com.example.covidmobilus.service.model.MovelAverageModel
import com.example.covidmobilus.service.repository.CasesRepository

class CasesViewModel(application: Application) : AndroidViewModel(application) {

    private val mCasesRepository = CasesRepository(application)

    private val mList = MutableLiveData<List<CasesModel>>()
    var list: LiveData<List<CasesModel>> = mList

    fun listCases(from: String, to: String) {
        mCasesRepository.listCases(from, to, object : APIListener<CasesModel> {
            override fun onSuccess(model: List<CasesModel>) {
                mList.value = model.reversed()

            }

            override fun onFailure(str: String) {
                mList.value = arrayListOf()
            }
        })
    }

    fun movelAverage(): MutableList<MovelAverageModel> {

        val movelAverageModels: MutableList<MovelAverageModel> = mutableListOf()

        mList.value?.forEachIndexed { index, casesModel ->
            val casesLastWeek = mList.value?.getOrNull(index + 7)?.cases ?: 0
            val movelAverage = (casesModel.cases - casesLastWeek) / 7

            movelAverageModels.add(
                MovelAverageModel(
                    date = casesModel.date,
                    movelAverage = "${movelAverage}"
                )
            )
            if (index >= 16) return movelAverageModels
        }
        return movelAverageModels
    }

}