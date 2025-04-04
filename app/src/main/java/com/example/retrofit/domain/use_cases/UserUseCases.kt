package com.example.retrofit.domain.use_cases

data class UserUseCases (
    val getUsers: GetUsers,
    val createUser: CreateUser,
    val updateUser: UpdateUser,
    val deleteUser: DeleteUser
)