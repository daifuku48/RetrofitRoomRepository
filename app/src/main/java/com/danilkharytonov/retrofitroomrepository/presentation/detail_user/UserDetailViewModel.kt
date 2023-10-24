package com.danilkharytonov.retrofitroomrepository.presentation.detail_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.danilkharytonov.retrofitroomrepository.domain.model.Login
import com.danilkharytonov.retrofitroomrepository.domain.model.Name
import com.danilkharytonov.retrofitroomrepository.domain.model.Picture
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class UserDetailViewModel constructor(
    private val getUserByIdFromDB: GetUserByIdFromDB,
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
}