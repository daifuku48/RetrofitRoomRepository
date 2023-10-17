package com.danilkharytonov.retrofitroomrepository.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<UserEntity>

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("SELECT * FROM USER_TABLE WHERE uuid ==:uuid")
    suspend fun getUserById(uuid: String): UserEntity
}