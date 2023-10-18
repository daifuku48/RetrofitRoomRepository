package com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list

import android.graphics.Bitmap
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import javax.inject.Inject

class LoadImagesFromStorageUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun execute(uuid: String): Bitmap? {
        return repository.loadImageFromStorage(uuid)
    }
}