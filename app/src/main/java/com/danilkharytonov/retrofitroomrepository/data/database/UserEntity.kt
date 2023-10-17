package com.danilkharytonov.retrofitroomrepository.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danilkharytonov.retrofitroomrepository.domain.model.Login
import com.danilkharytonov.retrofitroomrepository.domain.model.Name
import com.danilkharytonov.retrofitroomrepository.domain.model.Picture
import com.danilkharytonov.retrofitroomrepository.domain.model.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey
    val uuid: String,
    @ColumnInfo("email")
    val email: String,
    @ColumnInfo("gender")
    val gender: String,
    @ColumnInfo("icon_image")
    val imageIcon: String,
    @ColumnInfo("first_name")
    val firstName: String,
    @ColumnInfo("last_name")
    val lastName: String,
    @ColumnInfo("title")
    val title: String
)

fun UserEntity.toDomain(): User {
    return User(
        email = email,
        gender = gender,
        picture = Picture(iconImage = imageIcon),
        login = Login(uuid = uuid),
        name = Name(
            firstName = firstName,
            lastName = lastName,
            title = title
        )
    )
}