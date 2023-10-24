package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetUsersUseCase

class UserListViewModelFactory(
    private val getUsersUseCase: GetUsersUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserListViewModel(
            getUsersUseCase = getUsersUseCase
        ) as T
    }

}