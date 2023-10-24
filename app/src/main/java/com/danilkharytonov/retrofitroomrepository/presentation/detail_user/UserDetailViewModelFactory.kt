package com.danilkharytonov.retrofitroomrepository.presentation.detail_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB

class UserDetailViewModelFactory(
    private val getUserByIdFromDB: GetUserByIdFromDB
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserDetailViewModel(
            getUserByIdFromDB = getUserByIdFromDB
        ) as T
    }
}