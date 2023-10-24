package com.danilkharytonov.retrofitroomrepository.presentation.detail_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.danilkharytonov.retrofitroomrepository.domain.model.Login
import com.danilkharytonov.retrofitroomrepository.domain.model.Name
import com.danilkharytonov.retrofitroomrepository.domain.model.Picture
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class UserDetailViewModel(
    private val uuid: String,
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

    init {
        getUserById(uuid)
    }

    private fun getUserById(uuid: String) {
        viewModelScope.launch {
            _user.value = getUserByIdFromDB.execute(uuid = uuid)
        }
    }


    interface UserIdFactory {
        fun create(uuid: String): UserDetailViewModel
    }

    companion object {
        fun providesUserDetailViewModelFactory(
            factory: UserIdFactory,
            userUuid: String
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return factory.create(userUuid) as T
                }
            }
        }
    }
}