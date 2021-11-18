package com.example.covidmobilus.ui.viewholder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covidmobilus.R
import com.example.covidmobilus.service.model.MovelAverageModel
import java.text.SimpleDateFormat
import java.util.*

class CasesViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    private var mTextDate: TextView = itemView.findViewById(R.id.text_date)
    private var mTextMovelAverage: TextView = itemView.findViewById(R.id.text_movel_average)

    fun bindData(movelAverageModel: MovelAverageModel) {

        val date = SimpleDateFormat("yyyy-MM-dd").parse(movelAverageModel.date)
        this.mTextDate.text = mDateFormat.format(date)

        this.mTextMovelAverage.text = movelAverageModel.movelAverage
    }
}