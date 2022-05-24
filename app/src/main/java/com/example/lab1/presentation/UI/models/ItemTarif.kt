package com.example.lab1.presentation.UI.models

data class ItemTarif(val titleTarif: String, val desc: String, val cost: String, val onClick: ()-> Unit) : Item(titleTarif)
