package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.domain.repository.UserApiRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserDBRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserStorageRepository
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetUsersUseCase
import dagger.Module
import dagger.Provides


@Module
class DomainModule {

    @Provides
    fun providesGetAllUsersFromApiUseCase(
        apiRepository: UserApiRepository,
        dbRepository: UserDBRepository,
        storageRepository: UserStorageRepository
    ): GetUsersUseCase {
        return GetUsersUseCase(
            apiRepository = apiRepository,
            dbRepository = dbRepository,
            storageRepository = storageRepository
        )
    }

    @Provides
    fun providesGetUserByIdFromDBUseCase(
        repository: UserDBRepository
    ): GetUserByIdFromDB {
        return GetUserByIdFromDB(
            repository = repository
        )
    }
}