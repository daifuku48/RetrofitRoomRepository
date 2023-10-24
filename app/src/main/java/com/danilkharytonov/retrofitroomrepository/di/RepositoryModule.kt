package com.danilkharytonov.retrofitroomrepository.di

import android.app.Application
import com.danilkharytonov.retrofitroomrepository.data.database.UserDao
import com.danilkharytonov.retrofitroomrepository.data.network.UserRetrofitInstance
import com.danilkharytonov.retrofitroomrepository.data.repository.UserApiRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.data.repository.UserDBRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.data.repository.UserStorageRepositoryImpl
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserApiRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserDBRepository
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserStorageRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule(val context: Application) {

    @Provides
    fun providesContext(): Application {
        return context
    }

    @Provides
    fun providesUserDBRepository(
        userDao: UserDao
    ): UserDBRepository {
        return UserDBRepositoryImpl(
            userDao = userDao,
        )
    }

    @Provides
    fun providesUserApiRepository(
        retrofitInstance: UserRetrofitInstance
    ): UserApiRepository {
        return UserApiRepositoryImpl(
            userRetrofitInstance = retrofitInstance
        )
    }

    @Provides
    fun providesUserStorageRepository(
        context: Application
    ): UserStorageRepository {
        return UserStorageRepositoryImpl(
            context = context
        )
    }
}