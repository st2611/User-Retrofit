package com.example.retrofit.domain.repository

import com.example.retrofit.domain.model.User

interface UserRepository {

    suspend fun getUsers(): List<User>

    suspend fun createUser(user: User): User

    suspend fun updateUser(id: Int, user: User): User

    suspend fun deleteUser(id: Int)
}