package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
) : ViewModel() {
    private val _userList = MutableStateFlow<List<User>>(emptyList())
    val userList = _userList.asStateFlow()

    init {
        fetchUsersToEnd()
    }

    fun fetchUsersToEnd() {
        viewModelScope.launch {
            addUsersToEnd()
        }
    }

    fun fetchUserToStart() {
        viewModelScope.launch {
            addUsersToStart()
        }
    }

    private suspend fun addUsersToEnd() {
        val users = getUsersUseCase.execute(20)
        _userList.value = _userList.value + users
    }

    private suspend fun addUsersToStart() {
        val users = getUsersUseCase.execute(20)
        _userList.value = users + _userList.value
    }
}