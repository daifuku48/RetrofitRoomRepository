package com.danilkharytonov.retrofitroomrepository.presentation.detail_user

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilkharytonov.retrofitroomrepository.domain.model.Login
import com.danilkharytonov.retrofitroomrepository.domain.model.Name
import com.danilkharytonov.retrofitroomrepository.domain.model.Picture
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.LoadImagesFromStorageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserByIdFromDB: GetUserByIdFromDB,
    private val loadImagesFromStorageUseCase: LoadImagesFromStorageUseCase
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

    private val _userImage = MutableLiveData<Bitmap>()
    val userImage :LiveData<Bitmap> get() = _userImage

    fun getUserById(uuid: String) {
        viewModelScope.launch {
            _user.value = getUserByIdFromDB.execute(uuid = uuid)
            loadImage()
        }
    }

    private fun loadImage() {
        viewModelScope.launch {
            val bitmap = loadImagesFromStorageUseCase.execute(_user.value.login.uuid)
            Log.d("bitmap", bitmap.toString())
            if (bitmap != null) {
                _userImage.value = bitmap!!
            }
        }
    }
}