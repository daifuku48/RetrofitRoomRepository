package com.danilkharytonov.retrofitroomrepository.presentation.detail_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilkharytonov.retrofitroomrepository.domain.model.Login
import com.danilkharytonov.retrofitroomrepository.domain.model.Name
import com.danilkharytonov.retrofitroomrepository.domain.model.Picture
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserByIdFromDB: GetUserByIdFromDB
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
            _user.emit(getUserByIdFromDB.execute(uuid = uuid))
        }
    }
}