package com.danilkharytonov.retrofitroomrepository.network.model

import com.danilkharytonov.retrofitroomrepository.data.model.Name
import com.danilkharytonov.retrofitroomrepository.data.model.Picture

data class UserNetwork(
    val email: String,
    val gender: String,
    val name: NameNetwork,
    val picture: PictureNetwork,
)