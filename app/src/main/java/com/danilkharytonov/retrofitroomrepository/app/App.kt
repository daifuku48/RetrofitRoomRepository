package com.danilkharytonov.retrofitroomrepository.app

import android.app.Application
import com.danilkharytonov.retrofitroomrepository.di.AppComponent
import com.danilkharytonov.retrofitroomrepository.di.DaggerAppComponent
import com.danilkharytonov.retrofitroomrepository.di.RepositoryModule

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .repositoryModule(RepositoryModule(context = this))
            .build()
    }
}