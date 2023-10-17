package com.danilkharytonov.retrofitroomrepository.data.network.model

import com.google.gson.annotations.SerializedName

data class NameNetwork(
    @SerializedName("first")
    val firstName: String,
    @SerializedName("last")
    val lastName: String,
    @SerializedName("title")
    val title: String
)