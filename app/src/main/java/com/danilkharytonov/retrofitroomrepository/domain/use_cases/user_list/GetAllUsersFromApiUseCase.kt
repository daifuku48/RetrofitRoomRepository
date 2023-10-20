package com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list

import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserApiRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserDBRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserStorageRepository
import java.net.UnknownHostException
import javax.inject.Inject

class GetAllUsersFromApiUseCase @Inject constructor(
    private val apiRepository: UserApiRepository,
    private val dbRepository: UserDBRepository,
    private val storageRepository: UserStorageRepository
) {
    private var isFirstTime = true
    suspend fun execute(results: Int): List<User> {
        return try {
            val users = apiRepository.getAllUsersFromApi(results)
            dbRepository.insertUsersToDB(users)
            if (!isFirstTime) {
                dbRepository.deleteUsersFromDB()
                isFirstTime = false
            }
            storageRepository.saveUserImagesInStorage(users)
            users
        } catch (e: UnknownHostException) {
            dbRepository.getAllUsersFromDB()
        }
    }
}