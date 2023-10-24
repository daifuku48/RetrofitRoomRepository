package com.danilkharytonov.retrofitroomrepository.di

import androidx.room.Room
import com.danilkharytonov.retrofitroomrepository.data.database.UserDatabase
import org.koin.dsl.module


val dataBaseModule = module {
    factory {
        Room.databaseBuilder(
            context = get(),
            UserDatabase::class.java,
            "user_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<UserDatabase>().getUserDao()
    }
}