package com.example.retrofit.domain.use_cases

import com.example.retrofit.domain.model.User
import com.example.retrofit.domain.repository.UserRepository

class CreateUser (
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User) = userRepository.createUser(user)
}