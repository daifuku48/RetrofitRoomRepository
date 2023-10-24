package com.danilkharytonov.retrofitroomrepository.data.repository

import com.danilkharytonov.retrofitroomrepository.data.database.UserDao
import com.danilkharytonov.retrofitroomrepository.data.database.toDomain
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.model.toEntity
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserDBRepository

class UserDBRepositoryImpl constructor(
    private val userDao: UserDao,
) : UserDBRepository {
    override suspend fun deleteUsersFromDB() {
        userDao.deleteUsers()
    }

    override suspend fun insertUsersToDB(userList: List<User>) {
        userDao.insertUsers(userList.map { user ->
            user.toEntity()
        })
    }

    override suspend fun getAllUsersFromDB(): List<User> {
        return userDao.getAllUsers().map { userEntity ->
            userEntity.toDomain()
        }
    }

    override suspend fun getUserByIdFromDB(uuid: String): User {
        return userDao.getUserById(uuid).toDomain()
    }
}