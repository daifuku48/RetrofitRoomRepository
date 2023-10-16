package com.danilkharytonov.retrofitroomrepository.network.model

import com.google.gson.annotations.SerializedName

data class NameNetwork(
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String,
    @SerializedName("title")
    val title: String
)