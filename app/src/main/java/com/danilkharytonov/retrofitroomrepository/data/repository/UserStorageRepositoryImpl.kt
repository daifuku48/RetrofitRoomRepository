package com.danilkharytonov.retrofitroomrepository.data.repository

import android.app.Application
import android.util.Log
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserStorageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import javax.inject.Inject

class UserStorageRepositoryImpl @Inject constructor(
    private val context: Application
): UserStorageRepository {
    override suspend fun saveUserImagesInStorage(userList: List<User>) {
        withContext(Dispatchers.IO) {
            try {
                val dir = context.filesDir
                val files = dir.listFiles()
                files?.map { file ->
                    async {
                        try {
                            file.delete()
                        } catch (e: Exception) {
                            Log.e("Failed to delete file", file.name, e)
                        }
                    }
                }?.forEach {
                    it.await()
                }

                userList.map { user ->
                    async {
                        try {
                            val imageFile = File(dir, "${user.login.uuid}.jpg")
                            if (!imageFile.exists()) {
                                imageFile.createNewFile()
                            }
                            val imageUrl = user.picture.iconImage
                            val input = URL(imageUrl).openStream()
                            val output = FileOutputStream(imageFile)
                            input.copyTo(output)
                            output.close()
                        } catch (e: Exception) {
                            Log.e("Failed to save image", user.login.uuid, e)
                        }
                    }
                }.forEach {
                    it.await()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}