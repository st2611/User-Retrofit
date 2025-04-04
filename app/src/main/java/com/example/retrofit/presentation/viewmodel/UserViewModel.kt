package com.example.retrofit.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit.domain.model.User
import com.example.retrofit.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _name = mutableStateOf(" ")
    val name: State<String> = _name

    private val _email = mutableStateOf(" ")
    val email: State<String> = _email


    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = userRepository.getUsers()
                _users.value = userList
                Log.d("UserViewModel", "Users fetched: $userList")
            } catch (e: Exception) {
                Log.e("UserViewModel", "Error fetching users ${e.message}")
            }
        }
    }

//    fun addUser(user: User) {
//        viewModelScope.launch {
//            try {
//                val newUser = userRepository.createUser(user)
//                _users.value += newUser
//                Log.d("UserViewModel","User added: $newUser")
//            } catch (e: Exception) {
//                Log.e("UserViewModel","Error adding user",e)
//            }
//        }
//    }
//
//    fun updateUser(user: User) {
//        val userId = user.id
//        if (userId == null) {
//            Log.e("UserViewModel", "Cannot update user: ID is null")
//            return
//        }
//
//        viewModelScope.launch {
//            try {
//                val updatedUser = userRepository.updateUser(userId, user)
//                _users.value = _users.value.map { if (it.id == userId) updatedUser else it }
//                Log.d("UserViewModel", "User updated: $updatedUser")
//            } catch (e: Exception) {
//                Log.e("UserViewModel", "Error updating user", e)
//            }
//        }
//    }
//
//
//    fun deleteUser(userId: Int) {
//        viewModelScope.launch {
//            try {
//                userRepository.deleteUser(userId)
//                _users.value = _users.value.filter { it.id != userId }
//                Log.d("UserViewModel", "User deleted: $userId")
//            } catch (e: Exception) {
//                Log.e("UserViewModel", "Error deleting user", e)
//            }
//        }
//    }
}