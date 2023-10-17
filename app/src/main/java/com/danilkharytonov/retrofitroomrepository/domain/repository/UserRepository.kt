package com.danilkharytonov.retrofitroomrepository.domain.repository

import android.graphics.Bitmap
import com.danilkharytonov.retrofitroomrepository.domain.model.User

interface UserRepository {
    suspend fun getAllUsersFromApi(results: Int): List<User>

    suspend fun deleteUserFromDB(user: User, bitmap: Bitmap)

    suspend fun insertUserToDB(user: User, bitmap: Bitmap)

    suspend fun getAllUsersFromDB(): List<User>

    suspend fun getUserByIdFromDB(uuid: String): User
}