package com.danilkharytonov.retrofitroomrepository.presentation.detail_user

import android.app.Application
import android.os.Environment
import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.danilkharytonov.retrofitroomrepository.R
import com.danilkharytonov.retrofitroomrepository.domain.model.Login
import com.danilkharytonov.retrofitroomrepository.domain.model.Name
import com.danilkharytonov.retrofitroomrepository.domain.model.Picture
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB
import com.danilkharytonov.retrofitroomrepository.presentation.activity.MainActivity.Companion.IMAGE_DIRECTORY_PATH
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject


@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserByIdFromDB: GetUserByIdFromDB,
    private val context: Application
) : ViewModel() {
    private val _user = MutableStateFlow(
        User(
            email = "",
            gender = "",
            name = Name("", "", ""),
            picture = Picture(""),
            login = Login("")
        )
    )

    val user = _user.asStateFlow()

    fun getUserById(uuid: String) {
        viewModelScope.launch {
            _user.value = getUserByIdFromDB.execute(uuid = uuid)
        }
    }


    fun loadImageFromStorage(userImage: ImageView) {
        val storageDir = File(
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            IMAGE_DIRECTORY_PATH
        )
        val fileName = "${user.value.login.uuid}.jpg"
        val imageFile = File(storageDir, fileName)
        Glide.with(context)
            .load(imageFile)
            .error(R.drawable.default_icon)
            .into(userImage)
    }
}