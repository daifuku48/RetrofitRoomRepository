package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.domain.repository.UserApiRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserDBRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserStorageRepository
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetUsersUseCase
import org.koin.dsl.module


val domainModule = module {
    single {
        GetUsersUseCase(
            apiRepository = get(),
            dbRepository = get(),
            storageRepository = get()
        )
    }

    single {
        GetUserByIdFromDB(
            repository = get()
        )
    }
}