package com.danilkharytonov.retrofitroomrepository.domain.repository

import com.danilkharytonov.retrofitroomrepository.data.model.Users

interface UserRepository {
    suspend fun getAllUsers(results: Int): Users
}