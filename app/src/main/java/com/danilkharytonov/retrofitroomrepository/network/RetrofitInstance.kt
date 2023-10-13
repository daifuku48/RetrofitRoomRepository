package com.danilkharytonov.retrofitroomrepository.network

import com.danilkharytonov.retrofitroomrepository.network.model.UsersNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInstance {

    @GET
    fun getAllUsers(@Query("results") results: String) : UsersNetwork

    companion object{
        const val BASE_URL = "https://randomuser.me/api/"
    }
}