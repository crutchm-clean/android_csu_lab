package com.example.lab1.presentation.di

import com.example.lab1.presentation.UI.MainActivity
import dagger.Component


@Component(modules = [AppModule::class,])
interface AppComponent {
    fun inject(activity: MainActivity)
}