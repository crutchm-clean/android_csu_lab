package com.example.lab1

import android.view.LayoutInflater
import android.view.ViewGroup
import  androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.lab1.presentation.UI.ViewHolder
import com.example.lab1.presentation.UI.models.Item
import com.example.lab1.presentation.UI.models.ItemInfo
import com.example.lab1.presentation.UI.models.ItemTarif

class Adapter : ListAdapter<Item, ViewHolder>(CardCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        when(viewType){
            1 ->{
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_tarif, parent, false)
            }
            2 ->{
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_info, parent, false)
            }
            else -> {
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.layout_item_title, parent, false)
            }
        })


    override fun getItemViewType(position: Int): Int = when(currentList[position]){
        is ItemTarif -> 1
        is ItemInfo -> 2
        else -> 3
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

}

class CardCallBack : DiffUtil.ItemCallback<Item>(){
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean = oldItem == newItem

}

