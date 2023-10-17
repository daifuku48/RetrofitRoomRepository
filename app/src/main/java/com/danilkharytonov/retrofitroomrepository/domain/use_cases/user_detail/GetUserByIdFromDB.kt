package com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail

import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import javax.inject.Inject

class GetUserByIdFromDB @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun execute(uuid: String): User {
        return repository.getUserByIdFromDB(uuid)
    }
}