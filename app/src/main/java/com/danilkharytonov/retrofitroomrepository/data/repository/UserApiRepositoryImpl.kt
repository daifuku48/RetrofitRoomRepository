package com.danilkharytonov.retrofitroomrepository.data.repository

import com.danilkharytonov.retrofitroomrepository.data.network.UserRetrofitInstance
import com.danilkharytonov.retrofitroomrepository.data.network.model.mapToDomain
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserApiRepository

class UserApiRepositoryImpl constructor(
    private val userRetrofitInstance: UserRetrofitInstance
) : UserApiRepository {
    override suspend fun getAllUsersFromApi(results: Int): List<User> {
        return userRetrofitInstance.getAllUsers(results).mapToDomain().userList
    }
}