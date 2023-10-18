package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.DeleteUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromApiUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.InsertUsersToDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.SaveUserImagesInStorageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getAllUsersFromApiUseCase: GetAllUsersFromApiUseCase,
    private val deleteUserFromDBUseCase: DeleteUsersFromDBUseCase,
    private val getAllUsersFromDBUseCase: GetAllUsersFromDBUseCase,
    private val insertUserToDBUseCase: InsertUsersToDBUseCase,
    private val saveUserImagesInStorageUseCase: SaveUserImagesInStorageUseCase,
) : ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList.asStateFlow()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                addUsers()
                deleteUserFromDBUseCase.execute()
                saveUsers()
                saveUserImagesInStorageUseCase.execute(userList.value)
            } catch (e: UnknownHostException) {
                getUsersFromDB()
            }
        }
    }

    private suspend fun saveUsers() {
        withContext(Dispatchers.IO) {
            insertUserToDBUseCase.execute(userList.value)
        }
    }

    private suspend fun addUsers() {
        Log.d("users", "users")
        val users = getAllUsersFromApiUseCase.execute(20)
        _userList.value = users
    }

    private suspend fun getUsersFromDB() {
        val users = getAllUsersFromDBUseCase.execute()
        _userList.value = users
    }
}