package com.danilkharytonov.retrofitroomrepository.data.network.model

import com.google.gson.annotations.SerializedName

data class LoginNetwork(
    @SerializedName("uuid")
    val uuid: String
)
