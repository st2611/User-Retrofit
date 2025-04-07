package com.example.retrofit.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.domain.model.User
import com.example.retrofit.domain.use_cases.UserUseCases
import com.example.retrofit.presentation.event.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _name = mutableStateOf(" ")
    val name: State<String> = _name

    private val _email = mutableStateOf(" ")
    val email: State<String> = _email

    private val _id = mutableStateOf(" ")
    val id: State<String> = _id


    init {
        fetchUsers()
    }

    fun onEvent(event: UserEvent) {

        when (event) {
            is UserEvent.DeleteUser -> {
                viewModelScope.launch {
                    Log.d("UserViewModel", "Id User Deleted: ${event.userId}")
                    userUseCases.deleteUser(event.userId)
                    fetchUsers()
                }
            }

            is UserEvent.EnterId -> {
                _id.value = event.id
            }

            is UserEvent.EnterEmail -> {
                _email.value = event.email
            }

            is UserEvent.EnterName -> {
                _name.value = event.name
            }

            is UserEvent.SaveUser -> {
                viewModelScope.launch {
                    val newUser = User(
                        id = _id.value.trim().toInt(),
                        name = _name.value.trim(),
                        email = _email.value.trim()
                    )
                    userUseCases.createUser(newUser)
                    _id.value = ""
                    _name.value = ""
                    _email.value = ""
                    fetchUsers()
                    Log.d(
                        "UserViewModel",
                        "Create New User: id = ${newUser.id}, name = ${newUser.name}, email = ${newUser.email}"
                    )
                }
            }

            is UserEvent.UpdateUser -> {
                viewModelScope.launch {
                    userUseCases.updateUser(event.id, event.user)
                    _id.value = ""
                    _name.value = ""
                    _email.value = ""
                    fetchUsers()
                    Log.d("UserViewModel", "Update User")
                }
            }
        }
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = userUseCases.getUsers()
                _users.value = userList
                Log.d("UserViewModel", "Users fetched: $userList")
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching users ${e.message}")
            }
        }
    }

    fun clearForm() {
        onEvent(UserEvent.EnterId(""))
        onEvent(UserEvent.EnterName(""))
        onEvent(UserEvent.EnterEmail(""))
    }
}