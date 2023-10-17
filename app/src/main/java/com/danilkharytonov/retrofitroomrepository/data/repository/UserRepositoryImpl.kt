package com.danilkharytonov.retrofitroomrepository.data.repository

import android.graphics.Bitmap
import com.danilkharytonov.retrofitroomrepository.data.database.UserDao
import com.danilkharytonov.retrofitroomrepository.data.database.toDomain
import com.danilkharytonov.retrofitroomrepository.data.network.UserRetrofitInstance
import com.danilkharytonov.retrofitroomrepository.data.network.model.mapToDomain
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.model.toEntity
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRetrofitInstance: UserRetrofitInstance,
    private val userDao: UserDao
) : UserRepository {
    override suspend fun getAllUsersFromApi(results: Int): List<User> {
        return userRetrofitInstance.getAllUsers(results).mapToDomain().userList
    }

    override suspend fun deleteUserFromDB(user: User, bitmap: Bitmap) {
        userDao.deleteUser(user.toEntity(bitmap))
    }


    override suspend fun insertUserToDB(user: User, bitmap: Bitmap) {
        userDao.insertUser(user.toEntity(bitmap))
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