package com.example.lab1.presentation

import android.app.Application
import com.example.lab1.presentation.di.AppComponent
import com.example.lab1.presentation.di.DaggerAppComponent

class App: Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }

}