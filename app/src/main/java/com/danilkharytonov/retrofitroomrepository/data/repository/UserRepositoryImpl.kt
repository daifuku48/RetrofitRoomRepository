package com.danilkharytonov.retrofitroomrepository.data.repository

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import com.danilkharytonov.retrofitroomrepository.data.database.UserDao
import com.danilkharytonov.retrofitroomrepository.data.database.toDomain
import com.danilkharytonov.retrofitroomrepository.data.network.UserRetrofitInstance
import com.danilkharytonov.retrofitroomrepository.data.network.model.mapToDomain
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.model.toEntity
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
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
                files?.forEach { file ->
                    if (file.delete()) {
                        Log.d("Deleted file", file.name)
                    } else {
                        Log.e("Failed to delete file", file.name)
                    }
                }

                Log.d("save file", "negr")

                userList.forEach { user ->
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
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: SecurityException) {
                e.printStackTrace()
            }
        }
    }

    override suspend fun loadImageFromStorage(uuid: String): Bitmap? {
        return withContext(Dispatchers.IO) {
            try {
                val dir = context.filesDir
                val imageFile = File(dir, "$uuid.jpg")
                if (imageFile.exists()) {
                    val input = FileInputStream(imageFile)
                    val bitmap = BitmapFactory.decodeStream(input)
                    input.close()
                    return@withContext bitmap
                }
                return@withContext null
            } catch (e: IOException) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}