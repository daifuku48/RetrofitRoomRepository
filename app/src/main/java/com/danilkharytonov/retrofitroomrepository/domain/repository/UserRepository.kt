package com.danilkharytonov.retrofitroomrepository.domain.repository

import com.danilkharytonov.retrofitroomrepository.domain.model.User

interface UserRepository {
    suspend fun getAllUsers(results: Int): List<User>
}