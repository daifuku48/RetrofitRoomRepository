package com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list

import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import javax.inject.Inject

class GetAllUsersFromDBUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun execute(): List<User> {
        return repository.getAllUsersFromDB()
    }

}