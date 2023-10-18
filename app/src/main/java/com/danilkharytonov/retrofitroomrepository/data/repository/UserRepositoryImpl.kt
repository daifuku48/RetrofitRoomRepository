package com.danilkharytonov.retrofitroomrepository.data.repository

import android.app.Application
import android.util.Log
import com.danilkharytonov.retrofitroomrepository.data.database.UserDao
import com.danilkharytonov.retrofitroomrepository.data.database.toDomain
import com.danilkharytonov.retrofitroomrepository.data.network.UserRetrofitInstance
import com.danilkharytonov.retrofitroomrepository.data.network.model.mapToDomain
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.model.toEntity
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRetrofitInstance: UserRetrofitInstance,
    private val userDao: UserDao,
    private val context: Application
) : UserRepository {
    override suspend fun getAllUsersFromApi(results: Int): List<User> {
        return userRetrofitInstance.getAllUsers(results).mapToDomain().userList
    }

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

    override suspend fun saveUserImagesInStorage(userList: List<User>) {
        withContext(Dispatchers.IO) {
            try {
                val dir = context.filesDir
                val files = dir.listFiles()
                files?.map { file ->
                    async {
                        try {
                            if (file.delete()) {
                                Log.d("Deleted file", file.name)
                            } else {
                                Log.e("Failed to delete file", file.name)
                            }
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

                            Log.d("Saved file", imageFile.name)
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