package com.danilkharytonov.retrofitroomrepository.di

import android.os.Bundle
import com.danilkharytonov.retrofitroomrepository.presentation.activity.MainActivity.Companion.USER_ID
import com.danilkharytonov.retrofitroomrepository.presentation.detail_user.UserDetailViewModel
import com.danilkharytonov.retrofitroomrepository.presentation.detail_user.UserDetailViewModelFactory
import com.danilkharytonov.retrofitroomrepository.presentation.user_list.UserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelModule = module {
    viewModel {
        UserListViewModel(
            getUsersUseCase = get()
        )
    }

    factory { (uuid: String) ->
        UserDetailViewModelFactory(uuid, get())
    }

    viewModel { (arguments: Bundle) ->
        UserDetailViewModelFactory((arguments.getString(USER_ID) ?: "0"), get())
            .create(UserDetailViewModel::class.java)
    }
}