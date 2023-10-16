package com.danilkharytonov.retrofitroomrepository.network.model

import com.google.gson.annotations.SerializedName

data class UserNetwork(
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("name")
    val name: NameNetwork,
    @SerializedName("picture")
    val picture: PictureNetwork,
)