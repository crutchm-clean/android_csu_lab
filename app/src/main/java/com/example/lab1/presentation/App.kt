package com.example.lab1.presentation

import android.app.Application
import com.example.lab1.presentation.di.AppComponent

class App: Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }
    override fun onCreate() {
        super.onCreate()
    }

}