package com.danilkharytonov.retrofitroomrepository.network.model

import com.danilkharytonov.retrofitroomrepository.data.model.User
import com.google.gson.annotations.SerializedName

data class UsersNetwork(
    @SerializedName("user")
    val user: List<UserNetwork>
)