package com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list

import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import javax.inject.Inject

class GetAllUsersFromApiUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun execute(results: Int): List<User> {
        return repository.getAllUsersFromApi(results)
    }
}