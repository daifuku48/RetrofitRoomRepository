package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.data.database.UserDao
import com.danilkharytonov.retrofitroomrepository.data.network.UserRetrofitInstance
import com.danilkharytonov.retrofitroomrepository.data.repository.UserApiRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.data.repository.UserDBRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.data.repository.UserStorageRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserApiRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserDBRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserStorageRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<UserDBRepository> {
        UserDBRepositoryImpl(
            userDao = get<UserDao>()
        )
    }

    single<UserApiRepository> {
        UserApiRepositoryImpl(
            userRetrofitInstance = get<UserRetrofitInstance>()
        )
    }

    single<UserStorageRepository> {
        UserStorageRepositoryImpl(
            context = get()
        )
    }
}