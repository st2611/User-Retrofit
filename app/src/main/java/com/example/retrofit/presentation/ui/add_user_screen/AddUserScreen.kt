package com.example.retrofit.presentation.ui.add_user_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.retrofit.presentation.event.UserEvent
import com.example.retrofit.presentation.ui.user_common.UserForm
import com.example.retrofit.presentation.viewmodel.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddUserScreen(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    val id = userViewModel.id.value
    val name = userViewModel.name.value
    val email = userViewModel.email.value
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (id.isBlank()) {
                        Toast.makeText(context, "Id is empty", Toast.LENGTH_SHORT).show()
                        return@FloatingActionButton
                    }

                    if (name.isBlank()) {
                        Toast.makeText(context, "Name is empty", Toast.LENGTH_SHORT).show()
                        return@FloatingActionButton
                    }

                    if (email.isBlank()) {
                        Toast.makeText(context, "Email is empty", Toast.LENGTH_SHORT).show()
                        return@FloatingActionButton
                    }

                    userViewModel.onEvent(UserEvent.SaveUser)
                    navController.navigateUp()
                    userViewModel.clearForm()
                },
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add User")
                }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        UserForm(
            id = id,
            name = name,
            email = email,
            onIdChange = { userViewModel.onEvent(UserEvent.EnterId(it)) },
            onNameChange = { userViewModel.onEvent(UserEvent.EnterName(it)) },
            onEmailChange = { userViewModel.onEvent(UserEvent.EnterEmail(it)) }
        )
    }

    DisposableEffect(Unit) {
        onDispose {
            userViewModel.clearForm()
        }
    }
}