package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.data.repository.UserApiRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.data.repository.UserDBRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.data.repository.UserStorageRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserApiRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserDBRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserStorageRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserDBRepository> {
        UserDBRepositoryImpl(
            userDao = get()
        )
    }

    factory<UserApiRepository> {
        UserApiRepositoryImpl(
            userRetrofitInstance = get()
        )
    }

    factory<UserStorageRepository> {
        UserStorageRepositoryImpl(
            context = get()
        )
    }
}