package com.danilkharytonov.retrofitroomrepository.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 5, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}