package com.example.retrofit.presentation.event

import com.example.retrofit.domain.model.User

sealed class UserEvent {
    data class DeleteUser(val userId: Int) : UserEvent()
    data class EnterId(val id: String) : UserEvent()
    data class EnterName(val name: String) : UserEvent()
    data class EnterEmail(val email: String) : UserEvent()
    data object SaveUser : UserEvent()
    data class UpdateUser(val id: Int, val user: User) : UserEvent()
}