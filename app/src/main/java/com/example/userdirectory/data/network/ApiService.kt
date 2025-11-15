package com.example.userdirectory.data.network

import com.example.userdirectory.data.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>
}
