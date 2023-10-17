package com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list

import android.graphics.Bitmap
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserFromDBUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun execute(user: User, bitmap: Bitmap) {
        repository.deleteUserFromDB(user, bitmap)
    }
}