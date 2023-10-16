package com.danilkharytonov.retrofitroomrepository.data.repository

import com.danilkharytonov.retrofitroomrepository.data.network.UserRetrofitInstance
import com.danilkharytonov.retrofitroomrepository.data.network.model.mapToDomain
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRetrofitInstance: UserRetrofitInstance,
) : UserRepository {
    override suspend fun getAllUsers(results: Int): List<User> {
        return userRetrofitInstance.getAllUsers(results).mapToDomain().userList
    }
}