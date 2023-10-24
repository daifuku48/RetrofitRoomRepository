package com.danilkharytonov.retrofitroomrepository.app

import android.app.Application
import com.danilkharytonov.retrofitroomrepository.di.AppComponent

class App : Application(){
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppC
    }
}