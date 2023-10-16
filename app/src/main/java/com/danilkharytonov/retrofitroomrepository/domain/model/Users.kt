package com.danilkharytonov.retrofitroomrepository.domain.model

import com.danilkharytonov.retrofitroomrepository.data.network.model.LoginNetwork
import com.danilkharytonov.retrofitroomrepository.data.network.model.NameNetwork
import com.danilkharytonov.retrofitroomrepository.data.network.model.PictureNetwork
import com.danilkharytonov.retrofitroomrepository.data.network.model.UserNetwork
import com.danilkharytonov.retrofitroomrepository.data.network.model.UsersNetwork

data class Users(
    val userList: List<User>
)

fun Users.mapToNetwork() : UsersNetwork {
    val userNetworkList = userList.map { user ->
        UserNetwork(
            email = user.email,
            gender = user.gender,
            name = NameNetwork(
                first = user.name.first,
                last = user.name.last,
                title = user.name.title
            ),
            login = LoginNetwork(
                user.login.uuid
            ),
            picture = PictureNetwork(
                thumbnail = user.picture.thumbnail
            )
        )
    }
    return UsersNetwork(userList = userNetworkList)
}