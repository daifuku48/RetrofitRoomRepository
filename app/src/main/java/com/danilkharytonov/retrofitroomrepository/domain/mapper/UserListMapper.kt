package com.danilkharytonov.retrofitroomrepository.domain.mapper

import com.danilkharytonov.retrofitroomrepository.data.model.Users
import com.danilkharytonov.retrofitroomrepository.network.model.UserNetwork
import com.danilkharytonov.retrofitroomrepository.network.model.UsersNetwork

class UserListMapper : Mapper<Users, UsersNetwork> {
    override fun mapToNetwork(domain: Users): UsersNetwork {
        return UsersNetwork()
    }

    override fun mapToDomain(network: UsersNetwork): Users {
        TODO("Not yet implemented")
    }
}