package com.danilkharytonov.retrofitroomrepository.data.network.model

import com.danilkharytonov.retrofitroomrepository.domain.model.Login
import com.danilkharytonov.retrofitroomrepository.domain.model.Name
import com.danilkharytonov.retrofitroomrepository.domain.model.Picture
import com.danilkharytonov.retrofitroomrepository.domain.model.User
import com.danilkharytonov.retrofitroomrepository.domain.model.Users
import com.google.gson.annotations.SerializedName

data class UsersNetwork(
    @SerializedName("results")
    val userList: List<UserNetwork>
)

fun UsersNetwork.mapToDomain(): Users {
    val userList = userList.map { userNetwork ->
        User(
            email = userNetwork.email,
            gender = userNetwork.gender,
            name = Name(
                first = userNetwork.name.first,
                last = userNetwork.name.last,
                title = userNetwork.name.title
            ),
            login = Login(
                uuid = userNetwork.login.uuid
            ),
            picture = Picture(
                thumbnail = userNetwork.picture.thumbnail
            )
        )
    }
    return Users(userList = userList)
}