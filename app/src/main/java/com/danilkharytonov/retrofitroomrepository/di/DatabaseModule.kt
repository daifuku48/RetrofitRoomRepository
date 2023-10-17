package com.danilkharytonov.retrofitroomrepository.di

import android.content.Context
import androidx.room.Room
import com.danilkharytonov.retrofitroomrepository.data.database.UserDao
import com.danilkharytonov.retrofitroomrepository.data.database.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): UserDatabase {
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