package com.danilkharytonov.retrofitroomrepository.network.model

import com.google.gson.annotations.SerializedName

data class PictureNetwork(
    @SerializedName("thumbnail")
    val thumbnail: String
)