package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import android.app.Application
import android.graphics.Bitmap
import android.os.Environment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.DeleteUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromApiUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.InsertUsersToDBUseCase
import com.danilkharytonov.retrofitroomrepository.presentation.activity.MainActivity.Companion.IMAGE_DIRECTORY_PATH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.net.UnknownHostException
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getAllUsersFromApiUseCase: GetAllUsersFromApiUseCase,
    private val deleteUserFromDBUseCase: DeleteUsersFromDBUseCase,
    private val getAllUsersFromDBUseCase: GetAllUsersFromDBUseCase,
    private val insertUserToDBUseCase: InsertUsersToDBUseCase,
    private val context: Application
) : ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList.asStateFlow()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                addUsers()
                deleteUserFromDBUseCase.execute()
                saveUsers()

            } catch (e: UnknownHostException) {
                getUsersFromDB()
            }
        }
    }

    private suspend fun saveUsers() {
        withContext(Dispatchers.IO) {
            userList.value.forEach { user ->
                Glide.with(context)
                    .asBitmap()
                    .load(user.picture.iconImage)
                    .listener(object : RequestListener<Bitmap> {
                        override fun onResourceReady(
                            resource: Bitmap,
                            model: Any,
                            target: com.bumptech.glide.request.target.Target<Bitmap>?,
                            dataSource: DataSource,
                            isFirstResource: Boolean
                        ): Boolean {
                            val storageDir = File(
                                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
                                IMAGE_DIRECTORY_PATH
                            )

                            if (!storageDir.exists()) {
                                storageDir.mkdirs()
                            }

                            val fileName = "${user.login.uuid}.jpg"
                            val imageFile = File(storageDir, fileName)
                            try {
                                val fos = FileOutputStream(imageFile)
                                resource.compress(Bitmap.CompressFormat.JPEG, 100, fos)
                                user.picture.iconImage = fileName
                                fos.flush()
                                fos.close()
                            } catch (e: Exception) {
                                e.printStackTrace()
                            }
                            return true
                        }

                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: com.bumptech.glide.request.target.Target<Bitmap>,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }
                    })
                    .submit()
            }
            insertUserToDBUseCase.execute(userList.value)
        }
    }

//    private suspend fun saveUsers(){
//        withContext(Dispatchers.IO){
//            userList.value.forEach { user ->
//                val cw = ContextWrapper(context)
//                val directory = cw.getDir(IMAGE_DIRECTORY_PATH, Context.MODE_APPEND)
//                val safeFileName = "image_${user.login.uuid.replace("-", "")}.jpg"
//                val myPath = File(directory, safeFileName)
//                Log.d("id", safeFileName)
//                var fos: FileOutputStream? = null
//                try {
//                    fos = FileOutputStream(myPath)
//                    val bitmapImage = Glide.with(context)
//                        .asBitmap()
//                        .load(user.picture.iconImage)
//                        .submit().get()
//                    bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
//                    user.picture.iconImage = safeFileName
//                } catch (e: java.lang.Exception) {
//                    e.printStackTrace()
//                } finally {
//                    try {
//                        fos!!.close()
//                    } catch (e: IOException) {
//                        e.printStackTrace()
//                    }
//                }
//            }
//            insertUserToDBUseCase.execute(userList.value)
//            Log.d("users", "users saved")
//        }
//    }

    private suspend fun addUsers() {
        val users = getAllUsersFromApiUseCase.execute(20)
        _userList.value = users
    }

    private suspend fun getUsersFromDB() {
        val users = getAllUsersFromDBUseCase.execute()
        _userList.value = users
    }
}