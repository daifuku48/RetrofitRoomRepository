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
    @ColumnInfo("image_bytes")
    val imageBytes: ByteArray,
    @ColumnInfo("first_name")
    val firstName: String,
    @ColumnInfo("last_name")
    val lastName: String,
    @ColumnInfo("title")
    val title: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as UserEntity
        if (uuid != other.uuid) return false
        if (email != other.email) return false
        if (!imageBytes.contentEquals(other.imageBytes)) return false
        return true
    }

    override fun hashCode(): Int {
        var result = uuid.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + imageBytes.contentHashCode()
        return result
    }
}

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
        ),
        byteArray = imageBytes
    )
}