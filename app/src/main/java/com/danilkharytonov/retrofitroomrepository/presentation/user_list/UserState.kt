package com.danilkharytonov.retrofitroomrepository.presentation.user_list

import com.danilkharytonov.retrofitroomrepository.data.model.Users

data class UserState(
    var users: Users = Users(emptyList())
)