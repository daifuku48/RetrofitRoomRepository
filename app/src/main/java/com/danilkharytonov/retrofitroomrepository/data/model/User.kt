package com.danilkharytonov.retrofitroomrepository.data.model

data class User(
    val email: String,
    val gender: String,
    val name: Name,
    val picture: Picture,
)