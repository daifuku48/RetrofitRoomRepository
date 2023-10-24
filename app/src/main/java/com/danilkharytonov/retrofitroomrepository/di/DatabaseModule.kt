package com.danilkharytonov.retrofitroomrepository.di

import android.app.Application
import androidx.room.Room
import com.danilkharytonov.retrofitroomrepository.data.database.UserDao
import com.danilkharytonov.retrofitroomrepository.data.database.UserDatabase
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {
    @Provides
    fun providesDatabase(context: Application): UserDatabase {
        return Room.databaseBuilder(
            context = context,
            UserDatabase::class.java,
            "user_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun providesUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.getUserDao()
    }
}