package com.danilkharytonov.retrofitroomrepository.domain.mapper

import com.danilkharytonov.retrofitroomrepository.data.model.Name
import com.danilkharytonov.retrofitroomrepository.data.model.Picture
import com.danilkharytonov.retrofitroomrepository.data.model.User
import com.danilkharytonov.retrofitroomrepository.data.model.Users
import com.danilkharytonov.retrofitroomrepository.network.model.NameNetwork
import com.danilkharytonov.retrofitroomrepository.network.model.PictureNetwork
import com.danilkharytonov.retrofitroomrepository.network.model.UserNetwork
import com.danilkharytonov.retrofitroomrepository.network.model.UsersNetwork

class UserListMapper : Mapper<Users, UsersNetwork> {
    override fun mapToNetwork(domain: Users): UsersNetwork {
        val userNetworkList = domain.user.map { user ->
            UserNetwork(
                email = user.email,
                gender = user.gender,
                name = NameNetwork(
                    first = user.name.first,
                    last = user.name.last,
                    title = user.name.title
                ),
                picture = PictureNetwork(
                    medium = user.picture.medium,
                    thumbnail = user.picture.thumbnail
                )
            )
        }
        return UsersNetwork(user = userNetworkList)
    }

    override fun mapToDomain(network: UsersNetwork): Users {
        val userList = network.user.map { userNetwork ->
            User(
                email = userNetwork.email,
                gender = userNetwork.gender,
                name = Name(
                    first = userNetwork.name.first,
                    last = userNetwork.name.last,
                    title = userNetwork.name.title
                ),
                picture = Picture(
                    medium = userNetwork.picture.medium,
                    thumbnail = userNetwork.picture.thumbnail
                )
            )
        }
        return Users(user = userList)
    }
}
