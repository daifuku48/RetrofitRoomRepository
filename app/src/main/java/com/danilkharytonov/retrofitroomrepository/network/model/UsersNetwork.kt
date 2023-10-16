package com.danilkharytonov.retrofitroomrepository.network.model

import com.google.gson.annotations.SerializedName

data class UsersNetwork(
    @SerializedName("results")
    val userList: List<UserNetwork>
)