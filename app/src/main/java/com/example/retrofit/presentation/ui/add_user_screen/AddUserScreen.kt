package com.example.retrofit.presentation.ui.add_user_screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.retrofit.domain.model.User
import com.example.retrofit.presentation.viewmodel.UserViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddUserScreen(
    navController: NavHostController,
    userViewModel: UserViewModel
) {
    var name = userViewModel.name.value
    var email = userViewModel.email.value
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (name.isBlank()) {
                        Toast.makeText(context,"Name is empty", Toast.LENGTH_SHORT).show()
                        return@FloatingActionButton
                    }

                    if (email.isBlank()) {
                        Toast.makeText(context,"Email is empty", Toast.LENGTH_SHORT).show()
                        return@FloatingActionButton
                    }

                    userViewModel.addUser(user = User(name = name, email = email))
                    navController.navigateUp()
                },
                content = {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add User")
                }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
        Column (
            modifier = Modifier.padding(8.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = {
                    Text("Name")
                },
                textStyle = MaterialTheme.typography.displayMedium,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                label = {
                    Text("Email")
                },
                textStyle = MaterialTheme.typography.displaySmall,
                modifier = Modifier.fillMaxSize().padding(bottom = 64.dp)
            )
        }
    }
}