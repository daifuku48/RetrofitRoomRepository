package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersUseCase
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
    fun provideGetAllUsersUseCase(
        repository: UserRepository
    ): GetAllUsersUseCase {
        return GetAllUsersUseCase(
            repository = repository
        )
    }
}