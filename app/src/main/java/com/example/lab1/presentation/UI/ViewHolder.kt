package com.example.lab1.presentation.UI

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.R
import com.example.lab1.presentation.UI.models.Item
import com.example.lab1.presentation.UI.models.ItemInfo
import com.example.lab1.presentation.UI.models.ItemTarif
import com.example.lab1.presentation.UI.models.ItemTitle

open class ViewHolder (private var view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Item){
        when(item){
            is ItemInfo ->{
                val info = item as ItemInfo
                val uname = view.findViewById<TextView>(R.id.user_name)
                uname.text = info.title
                val icon = view.findViewById<ImageView>(R.id.info_icon)
                icon.setImageDrawable(info.drawable)
            }

            is ItemTarif ->{
                val tarif = item as ItemTarif
                val text = view.findViewById<TextView>(R.id.text)
                text.text = tarif.title
                val desc = view.findViewById<TextView>(R.id.description)
                desc.text = tarif.desc
                val cost = view.findViewById<TextView>(R.id.cost)
                cost.text = tarif.cost
            }

            is ItemTitle ->{
                val tit = item as ItemTitle
                val title = view.findViewById<TextView>(R.id.title)
                title.text = tit.title
            }
        }
    }
}
