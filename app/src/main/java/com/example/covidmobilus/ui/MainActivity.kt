package com.example.covidmobilus.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covidmobilus.R
import com.example.covidmobilus.ui.adapter.CasesAdapter
import com.example.covidmobilus.viewmodel.AllDataViewModel
import com.example.covidmobilus.viewmodel.CasesViewModel
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mCasesViewModel: CasesViewModel
    private lateinit var mAllDataViewModel: AllDataViewModel

    private val mAdapter = CasesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mCasesViewModel = ViewModelProvider(this).get(CasesViewModel::class.java)
        mAllDataViewModel = ViewModelProvider(this).get(AllDataViewModel::class.java)

        val recycler = findViewById<RecyclerView>(R.id.recycler_list)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = mAdapter

        observe()
        getSixMonths()
        getDataLastMonth()
    }

    private fun getSixMonths() {
        mCasesViewModel.listCases("2021-05-15T00:00:00Z", "2021-11-15T00:00:00Z")
    }

    private fun getDataLastMonth(){
        mAllDataViewModel.listAllData()
        mAllDataViewModel.findData()
    }

    override fun onResume() {
        super.onResume()

        val list = mCasesViewModel.movelAverage()
        mAdapter.updateListener(list)
    }


    private fun observe() {
        mCasesViewModel.list.observe(this, Observer {
            if (it.count() > 0) {
                val list = mCasesViewModel.movelAverage()
                mAdapter.updateListener(list)
            }
        })

        mAllDataViewModel.list.observe(this, Observer {
            if (it.count() > 0){
                mAllDataViewModel.findData()
            }
        })



    }

    override fun onClick(view: View?) {

    }


}