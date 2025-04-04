package com.example.retrofit.domain.use_cases

import com.example.retrofit.domain.repository.UserRepository

class GetUsers(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() = userRepository.getUsers()
}