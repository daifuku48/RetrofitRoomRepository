package com.danilkharytonov.retrofitroomrepository.data.network.model

import com.google.gson.annotations.SerializedName

data class PictureNetwork(
    @SerializedName("thumbnail")
    val thumbnail: String
)