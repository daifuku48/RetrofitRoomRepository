package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetUsersUseCase
import com.danilkharytonov.retrofitroomrepository.presentation.detail_user.UserDetailViewModel
import com.danilkharytonov.retrofitroomrepository.presentation.detail_user.UserDetailViewModelFactory
import com.danilkharytonov.retrofitroomrepository.presentation.user_list.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel<UserListViewModel> {
        UserListViewModel(
            getUsersUseCase = get<GetUsersUseCase>()
        )
    }

    factory { (uuid: String) ->
        UserDetailViewModelFactory(uuid, get())
    }

    viewModel { (uuid: String) ->
        UserDetailViewModelFactory(uuid, get())
            .create(UserDetailViewModel::class.java)
    }
}