package com.danilkharytonov.retrofitroomrepository.di

import android.content.Context
import androidx.room.Room
import com.danilkharytonov.retrofitroomrepository.data.database.UserDao
import com.danilkharytonov.retrofitroomrepository.data.database.UserDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(context: Context): UserDatabase {
        return Room.databaseBuilder(
            context = context,
            UserDatabase::class.java,
            "user_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.getUserDao()
    }
}