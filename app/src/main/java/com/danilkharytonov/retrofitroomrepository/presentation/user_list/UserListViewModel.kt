package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.DeleteUserFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromApiUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersFromDBUseCase
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.InsertUserToDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getAllUsersFromApiUseCase: GetAllUsersFromApiUseCase,
    private val deleteUserFromDBUseCase: DeleteUserFromDBUseCase,
    private val getAllUsersFromDBUseCase: GetAllUsersFromDBUseCase,
    private val insertUserToDBUseCase: InsertUserToDBUseCase,
    private val context: Application
) : ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList.asStateFlow()

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val users = getAllUsersFromApiUseCase.execute(20)
                _userList.emit(users)
                val dbUsers = getAllUsersFromDBUseCase.execute()
                dbUsers.map { user ->
                    val bitmap = Glide.with(context)
                        .asBitmap()
                        .load(user.picture.iconImage)
                        .submit()
                        .get()
                    deleteUserFromDBUseCase.execute(user, bitmap)
                }
                saveUsers(users)
            } catch (e: Exception) {
                e.printStackTrace()
                val users = getAllUsersFromDBUseCase.execute()
                _userList.emit(users)
            }
        }
    }

    private suspend fun saveUsers(userList: List<User>) {
        withContext(Dispatchers.IO) {
            userList.forEach { user ->
                val bitmap = Glide.with(context)
                    .asBitmap()
                    .load(user.picture.iconImage)
                    .submit()
                    .get()
                insertUserToDBUseCase.execute(user, bitmap)
            }
        }
    }
}