package com.danilkharytonov.retrofitroomrepository.di

import android.app.Application
import com.danilkharytonov.retrofitroomrepository.data.database.UserDao
import com.danilkharytonov.retrofitroomrepository.data.network.UserRetrofitInstance
import com.danilkharytonov.retrofitroomrepository.data.repository.UserApiRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.data.repository.UserDBRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.data.repository.UserStorageRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserApiRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserDBRepository
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
    fun providesUserDBRepository(
        userDao: UserDao
    ): UserDBRepository {
        return UserDBRepositoryImpl(
            userDao = userDao,
        )
    }

    @Provides
    @Singleton
    fun providesUserApiRepository(
        retrofitInstance: UserRetrofitInstance
    ): UserApiRepository {
        return UserApiRepositoryImpl(
            userRetrofitInstance = retrofitInstance
        )
    }

    @Provides
    @Singleton
    fun providesUserStorageRepository(
        context: Application
    ): UserStorageRepositoryImpl {
        return UserStorageRepositoryImpl(
            context = context
        )
    }
}