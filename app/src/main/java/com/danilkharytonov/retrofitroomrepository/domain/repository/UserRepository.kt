package com.danilkharytonov.retrofitroomrepository.domain.repository

import com.danilkharytonov.retrofitroomrepository.domain.model.User

interface UserRepository {
    suspend fun getAllUsersFromApi(results: Int, page: Int): List<User>

    suspend fun deleteUsersFromDB()

    suspend fun insertUsersToDB(userList: List<User>)

    suspend fun getAllUsersFromDB(): List<User>

    suspend fun getUserByIdFromDB(uuid: String): User

    suspend fun saveUserImagesInStorage(userList: List<User>)

    suspend fun deleteImageFiles()
}