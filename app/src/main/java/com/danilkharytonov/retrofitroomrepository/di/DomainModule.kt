package com.danilkharytonov.retrofitroomrepository.di

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.danilkharytonov.retrofitroomrepository.domain.repository.UserRepository
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_detail.GetUserByIdFromDB
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.DeleteImageFilesUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.DeleteUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromApiUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.InsertUsersToDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.SaveUserImagesInStorageUseCase
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
    ): DeleteUsersFromDBUseCase {
        return DeleteUsersFromDBUseCase(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun providesInsertUserToDBUseCase(
        repository: UserRepository
    ): InsertUsersToDBUseCase {
        return InsertUsersToDBUseCase(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun providesSaveUserImagesInStorageUseCase(
        repository: UserRepository
    ): SaveUserImagesInStorageUseCase {
        return SaveUserImagesInStorageUseCase(
            repository = repository
        )
    }

    @Provides
    @Singleton
    fun providesDeleteImageFilesUseCase(
        repository: UserRepository
    ): DeleteImageFilesUseCase {
        return DeleteImageFilesUseCase(
            repository = repository
        )
    }
}

@GlideModule
class Module : AppGlideModule()