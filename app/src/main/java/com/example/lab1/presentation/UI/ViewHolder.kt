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
            is ItemInfo -> {
                val uname = view.findViewById<TextView>(R.id.user_name)
                uname.text = item.title
                val icon = view.findViewById<ImageView>(R.id.info_icon)
                icon.setImageDrawable(item.drawable)
            }

            is ItemTarif -> {
                val text = view.findViewById<TextView>(R.id.text)
                text.text = item.title
                val desc = view.findViewById<TextView>(R.id.description)
                desc.text = item.desc
                val cost = view.findViewById<TextView>(R.id.cost)
                cost.text = item.cost
            }

            is ItemTitle -> {
                val title = view.findViewById<TextView>(R.id.title)
                title.text = item.title
            }
        }
    }
}
