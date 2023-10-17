package com.danilkharytonov.retrofitroomrepository.domain.model

import android.graphics.Bitmap
import com.danilkharytonov.retrofitroomrepository.data.database.UserEntity
import java.io.ByteArrayOutputStream

data class User(
    val email: String,
    val gender: String,
    val login: Login,
    val name: Name,
    val picture: Picture,
    var byteArray: ByteArray? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as User
        if (email != other.email) return false
        if (gender != other.gender) return false
        if (login != other.login) return false
        if (name != other.name) return false
        if (picture != other.picture) return false
        if (byteArray != null) {
            if (other.byteArray == null) return false
            if (!byteArray.contentEquals(other.byteArray)) return false
        } else if (other.byteArray != null) return false
        return true
    }

    override fun hashCode(): Int {
        var result = email.hashCode()
        result = 31 * result + gender.hashCode()
        result = 31 * result + login.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + picture.hashCode()
        result = 31 * result + (byteArray?.contentHashCode() ?: 0)
        return result
    }
}

fun User.toEntity(bitmap: Bitmap): UserEntity {
    val stream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    return UserEntity(
        uuid = login.uuid,
        email = email,
        gender = gender,
        firstName = name.firstName,
        lastName = name.lastName,
        title = name.title,
        imageBytes = stream.toByteArray(),
        imageIcon = picture.iconImage
    )
}
