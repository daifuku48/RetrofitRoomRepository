package com.danilkharytonov.retrofitroomrepository.network.model

import com.danilkharytonov.retrofitroomrepository.data.model.User

data class UsersNetwork(
    val user: List<UserNetwork>
)