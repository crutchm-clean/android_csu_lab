package com.example.lab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import  androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.lab1.UI.ViewHolder
import com.example.lab1.UI.models.Item
import com.example.lab1.UI.models.ItemInfo
import com.example.lab1.UI.models.ItemTarif
import javax.security.auth.callback.Callback

class Adapter : ListAdapter<Item, ViewHolder>(CardCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        when(currentList[viewType]){
            is ItemTarif ->{
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_tarif, parent, false)
            }
            is ItemInfo ->{
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_info, parent, false)
            }
            else -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_title, parent, false)
            }
        })



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}

class CardCallBack : DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem

}

