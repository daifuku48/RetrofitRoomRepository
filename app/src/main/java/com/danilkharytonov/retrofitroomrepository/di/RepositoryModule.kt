package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.data.repository.UserRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.domain.mapper.UserListMapper
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import com.danilkharytonov.retrofitroomrepository.network.UserRetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesUserListMapper() : UserListMapper{
        return UserListMapper()
    }

    @Provides
    @Singleton
    fun providesUserRepository(
        userRetrofitInstance: UserRetrofitInstance,
        userListMapper: UserListMapper) : UserRepository {
        return UserRepositoryImpl(
            userRetrofitInstance = userRetrofitInstance,
            userListMapper = userListMapper
        )
    }

}