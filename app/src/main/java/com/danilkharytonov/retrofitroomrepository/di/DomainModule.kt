package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.DeleteUserFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromApiUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.InsertUserToDBUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun providesGetAllUsersFromApiUseCase(
        repository: UserRepository
    ): GetAllUsersFromApiUseCase {
        return GetAllUsersFromApiUseCase(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun providesGetAllUsersFromDB(
        repository: UserRepository
    ): GetAllUsersFromDBUseCase {
        return GetAllUsersFromDBUseCase(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun providesGetUserByIdFromDBUseCase(
        repository: UserRepository
    ): GetUserByIdFromDB {
        return GetUserByIdFromDB(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun providesDeleteUsersFromDBUseCase(
        repository: UserRepository
    ): DeleteUserFromDBUseCase {
        return DeleteUserFromDBUseCase(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun providesInsertUserToDBUseCase(
        repository: UserRepository
    ): InsertUserToDBUseCase {
        return InsertUserToDBUseCase(
            repository = repository
        )
    }
}