package com.danilkharytonov.retrofitroomrepository.domain.repository

import com.danilkharytonov.retrofitroomrepository.domain.model.User

interface UserDBRepository {
    suspend fun deleteUsersFromDB()

    suspend fun insertUsersToDB(userList: List<User>)

    suspend fun getAllUsersFromDB(): List<User>

    suspend fun getUserByIdFromDB(uuid: String): User
}