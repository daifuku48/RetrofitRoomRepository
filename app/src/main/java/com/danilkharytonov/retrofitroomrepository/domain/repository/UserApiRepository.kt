package com.danilkharytonov.retrofitroomrepository.domain.repository

import com.danilkharytonov.retrofitroomrepository.domain.model.User

interface UserApiRepository {
    suspend fun getAllUsersFromApi(results: Int): List<User>
}