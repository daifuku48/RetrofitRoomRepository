package com.danilkharytonov.retrofitroomrepository.di

import androidx.room.Room
import com.danilkharytonov.retrofitroomrepository.data.database.UserDao
import com.danilkharytonov.retrofitroomrepository.data.database.UserDatabase
import org.koin.dsl.module


val dataBaseModule = module {
    single<UserDatabase> {
        Room.databaseBuilder(
            context = get(),
            UserDatabase::class.java,
            "user_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    single<UserDao> {
        get<UserDatabase>().getUserDao()
    }
}