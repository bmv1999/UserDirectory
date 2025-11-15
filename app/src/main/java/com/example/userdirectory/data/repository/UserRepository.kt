package com.example.userdirectory.data.repository

import com.example.userdirectory.data.database.UserDao
import com.example.userdirectory.data.model.User
import com.example.userdirectory.data.network.ApiService
import kotlinx.coroutines.flow.Flow

class UserRepository(
    private val api: ApiService,
    private val userDao: UserDao
) {
    // Single source of truth: Room
    fun getUsersStream(query: String): Flow<List<User>> {
        return if (query.isBlank()) {
            userDao.getAllUsers()
        } else {
            userDao.searchUsers(query)
        }
    }

    // Offline-first refresh
    suspend fun refreshUsers() {
        val remoteUsers = api.getUsers()
        // If GET works, replace data in Room
        userDao.clearUsers()
        userDao.insertUsers(remoteUsers)
    }
}
