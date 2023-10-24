package com.danilkharytonov.retrofitroomrepository.app

import android.app.Application
import com.danilkharytonov.retrofitroomrepository.di.ViewModelModule
import com.danilkharytonov.retrofitroomrepository.di.apiModule
import com.danilkharytonov.retrofitroomrepository.di.dataBaseModule
import com.danilkharytonov.retrofitroomrepository.di.domainModule
import com.danilkharytonov.retrofitroomrepository.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(
                listOf(
                    apiModule,
                    domainModule,
                    repositoryModule,
                    dataBaseModule,
                    ViewModelModule
                )
            )
        }
    }
}
