package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.presentation.detail_user.UserDetailViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent


@EntryPoint
@InstallIn(FragmentComponent::class)
interface ViewModelFactoryProvider {
    fun providesUserDetailViewModelFactory(): UserDetailViewModel.UserIdFactory
}