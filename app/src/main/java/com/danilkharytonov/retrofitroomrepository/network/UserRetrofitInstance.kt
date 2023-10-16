package com.danilkharytonov.retrofitroomrepository.network

import com.danilkharytonov.retrofitroomrepository.network.model.UsersNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface UserRetrofitInstance {

    @GET("api/")
    suspend fun getAllUsers(@Query("results") results: Int): UsersNetwork

    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }
}