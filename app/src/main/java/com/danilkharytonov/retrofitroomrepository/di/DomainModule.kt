package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetUsersUseCase
import org.koin.dsl.module


val domainModule = module {
    factory {
        GetUsersUseCase(
            apiRepository = get(),
            dbRepository = get(),
            storageRepository = get()
        )
    }

    factory {
        GetUserByIdFromDB(
            repository = get()
        )
    }
}