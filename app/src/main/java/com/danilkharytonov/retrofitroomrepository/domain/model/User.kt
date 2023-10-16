package com.danilkharytonov.retrofitroomrepository.domain.model

data class User(
    val email: String,
    val gender: String,
    val login: Login,
    val name: Name,
    val picture: Picture,
)