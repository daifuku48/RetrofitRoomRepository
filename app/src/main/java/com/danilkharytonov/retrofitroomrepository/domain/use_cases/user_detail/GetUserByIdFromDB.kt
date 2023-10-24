package com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail

import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserDBRepository

class GetUserByIdFromDB constructor(
    private val repository: UserDBRepository
) {
    suspend fun execute(uuid: String): User {
        return repository.getUserByIdFromDB(uuid)
    }
}