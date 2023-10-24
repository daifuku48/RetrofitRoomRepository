package com.danilkharytonov.retrofitroomrepository.presentation.detail_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB


class UserDetailViewModelFactory(
    private val uuid: String,
    private val getUserByIdFromDB: GetUserByIdFromDB
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserDetailViewModel(
            uuid = uuid,
            getUserByIdFromDB = getUserByIdFromDB
        ) as T
    }
}