package com.example.covidmobilus.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covidmobilus.R
import com.example.covidmobilus.service.model.MovelAverageModel
import com.example.covidmobilus.ui.viewholder.CasesViewHolder

class CasesAdapter : RecyclerView.Adapter<CasesViewHolder>()   {

    private var mList: List<MovelAverageModel> = arrayListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateListener(list: List<MovelAverageModel>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CasesViewHolder {
        val item =
            LayoutInflater.from(parent.context).inflate(R.layout.row_list, parent, false)
        return CasesViewHolder(item)
    }

    override fun onBindViewHolder(holder: CasesViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.count()
    }
}