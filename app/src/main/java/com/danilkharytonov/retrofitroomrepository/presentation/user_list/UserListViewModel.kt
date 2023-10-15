package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilkharytonov.retrofitroomrepository.domain.use_cases.user_list.GetAllUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel(){

    val state: MutableLiveData<UserState> = MutableLiveData()

    fun fetchUsers() {
        viewModelScope.launch {
            Log.d("negr", "negr")
            state.value?.users = getAllUsersUseCase.execute("20")
        }
    }
}