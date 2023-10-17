package com.danilkharytonov.retrofitroomrepository.domain.model

import com.danilkharytonov.retrofitroomrepository.data.database.UserEntity

data class User(
    val email: String,
    val gender: String,
    val login: Login,
    val name: Name,
    val picture: Picture,
)

fun User.toEntity(): UserEntity {
    return UserEntity(
        uuid = login.uuid,
        email = email,
        gender = gender,
        firstName = name.firstName,
        lastName = name.lastName,
        title = name.title,
        imageIcon = picture.iconImage
    )
}
