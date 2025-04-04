package com.example.retrofit.domain.use_cases

import com.example.retrofit.domain.model.User
import com.example.retrofit.domain.repository.UserRepository

class UpdateUser(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id:Int, user: User) = userRepository.updateUser(id, user)
}