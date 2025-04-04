package com.example.retrofit.framework_driver.repository

import com.example.retrofit.domain.model.User
import com.example.retrofit.domain.repository.UserRepository
import com.example.retrofit.framework_driver.retrofit.ApiService

class UserRepositoryImpl(private val apiService: ApiService) : UserRepository {
    override suspend fun getUsers(): List<User> = apiService.getUsers()

    override suspend fun createUser(user: User) = apiService.createUser(user)

    override suspend fun updateUser(id: Int, user: User) = apiService.updateUser(id, user)

    override suspend fun deleteUser(id: Int) = apiService.deleteUser(id)
}