package com.example.retrofit.presentation.ui.user_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.retrofit.presentation.event.UserEvent
import com.example.retrofit.presentation.navigation.UserScreenDestination.ADD_USER_SCREEN
import com.example.retrofit.presentation.navigation.UserScreenDestination.UPDATE_USER_SCREEN
import com.example.retrofit.presentation.viewmodel.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserListScreen(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    val users by userViewModel.users.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(ADD_USER_SCREEN)
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Note"
                    )
                }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(users) { user ->
                UserItem(
                    user,
                    onUpdate = {
                        navController.navigate("$UPDATE_USER_SCREEN/${user.id}")

                    },
                    onDelete = {
                        userViewModel.onEvent(UserEvent.DeleteUser(user.id!!))
                    }
                )
            }
        }
    }
}