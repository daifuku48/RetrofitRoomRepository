package com.danilkharytonov.retrofitroomrepository.domain.repository

import com.danilkharytonov.retrofitroomrepository.domain.model.User

interface UserStorageRepository {
    suspend fun saveUserImagesInStorage(userList: List<User>)
}