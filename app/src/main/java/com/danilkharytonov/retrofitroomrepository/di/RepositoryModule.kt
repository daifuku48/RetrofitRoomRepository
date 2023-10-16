package com.danilkharytonov.retrofitroomrepository.di

import com.danilkharytonov.retrofitroomrepository.data.network.UserRetrofitInstance
import com.danilkharytonov.retrofitroomrepository.data.repository.UserRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
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
    fun providesUserRepository(
        userRetrofitInstance: UserRetrofitInstance,
    ): UserRepository {
        return UserRepositoryImpl(
            userRetrofitInstance = userRetrofitInstance,
        )
    }

}