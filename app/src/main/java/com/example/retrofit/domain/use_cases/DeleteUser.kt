package com.example.retrofit.domain.use_cases

import com.example.retrofit.domain.repository.UserRepository

class DeleteUser(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: Int) = userRepository.deleteUser(id)
}