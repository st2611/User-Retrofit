package com.example.retrofit.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.retrofit.presentation.navigation.UserScreenDestination.ADD_USER_SCREEN
import com.example.retrofit.presentation.navigation.UserScreenDestination.UPDATE_USER_SCREEN
import com.example.retrofit.presentation.navigation.UserScreenDestination.USER_LIST_SCREEN
import com.example.retrofit.presentation.ui.add_user_screen.AddUserScreen
import com.example.retrofit.presentation.ui.update_user_screen.UpdateUserScreen
import com.example.retrofit.presentation.ui.user_list_screen.UserListScreen
import com.example.retrofit.presentation.viewmodel.UserViewModel

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel = hiltViewModel()
) {
    NavHost(navController = navController, startDestination = USER_LIST_SCREEN) {
        composable(USER_LIST_SCREEN) {
            UserListScreen(navController = navController, userViewModel = userViewModel)
        }

        composable(ADD_USER_SCREEN) {
            AddUserScreen(navController = navController, userViewModel = userViewModel)
        }

        composable(
            route = "$UPDATE_USER_SCREEN/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: return@composable
            UpdateUserScreen(navController = navController, userId = userId, userViewModel = userViewModel)
        }
    }
}