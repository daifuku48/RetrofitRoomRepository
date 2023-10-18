package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.danilkharytonov.retrofitroomrepository.data.repository.UserPagingSource
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.DeleteImageFilesUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.DeleteUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromApiUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.InsertUsersToDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.SaveUserImagesInStorageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getAllUsersFromApiUseCase: GetAllUsersFromApiUseCase,
    private val deleteUserFromDBUseCase: DeleteUsersFromDBUseCase,
    private val getAllUsersFromDBUseCase: GetAllUsersFromDBUseCase,
    private val insertUserToDBUseCase: InsertUsersToDBUseCase,
    private val saveUserImagesInStorageUseCase: SaveUserImagesInStorageUseCase,
    private val deleteImageFilesUseCase: DeleteImageFilesUseCase
) : ViewModel() {

    val userList = Pager(PagingConfig(10)) {
        UserPagingSource(
            getAllUsersFromApiUseCase,
            saveUserImagesInStorageUseCase,
            insertUserToDBUseCase,
            getAllUsersFromDBUseCase,
            deleteUserFromDBUseCase,
            deleteImageFilesUseCase
        )
    }.flow.cachedIn(viewModelScope)
}