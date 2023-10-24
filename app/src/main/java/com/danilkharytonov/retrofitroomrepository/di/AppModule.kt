package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetUsersUseCase
import com.danilkharytonov.retrofitroomrepository.presentation.detail_user.UserDetailViewModelFactory
import com.danilkharytonov.retrofitroomrepository.presentation.user_list.UserListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun providesUserListViewModelFactory(getUsersUseCase: GetUsersUseCase): UserListViewModelFactory {
        return UserListViewModelFactory(
            getUsersUseCase = getUsersUseCase
        )
    }

    @Provides
    fun providesUserDetailViewModelFactory(getUserByIdFromDB: GetUserByIdFromDB): UserDetailViewModelFactory {
        return UserDetailViewModelFactory(
            getUserByIdFromDB = getUserByIdFromDB
        )
    }
}