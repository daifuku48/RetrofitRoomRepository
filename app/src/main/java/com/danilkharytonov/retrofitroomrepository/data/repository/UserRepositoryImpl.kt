package com.danilkharytonov.retrofitroomrepository.data.repository

import com.danilkharytonov.retrofitroomrepository.data.model.Users
import com.danilkharytonov.retrofitroomrepository.domain.mapper.UserListMapper
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import com.danilkharytonov.retrofitroomrepository.network.UserRetrofitInstance
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRetrofitInstance: UserRetrofitInstance,
    private val userListMapper: UserListMapper
) : UserRepository {
    override suspend fun getAllUsers(results: String): Users {
        return userListMapper.mapToDomain(userRetrofitInstance.getAllUsers(results))
    }
}