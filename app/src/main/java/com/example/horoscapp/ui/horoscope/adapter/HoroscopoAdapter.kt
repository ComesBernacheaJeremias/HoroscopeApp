package com.example.horoscapp.ui.horoscope.adapter

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.horoscapp.R
import com.example.horoscapp.domain.model.HoroscopeInfo

class HoroscopoAdapter(private var horoscopeList: List<HoroscopeInfo> = emptyList()): RecyclerView.Adapter<HoroscopoViewHolder>() {

    fun updateList (list: List<HoroscopeInfo>) {
        horoscopeList = list
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopoViewHolder {
        return HoroscopoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false))
    }

    override fun getItemCount() = horoscopeList.size



    override fun onBindViewHolder(holder: HoroscopoViewHolder, position: Int) {
        holder.render(horoscopeList[position])
    }


}