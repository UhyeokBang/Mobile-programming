package com.example.week07.example2

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

sealed class Routes(val route: String) {
    object Login : Routes("Login")
    object Welcome : Routes("Welcome")
    object Register : Routes("Register")
}

@Composable
fun LoginNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(route = Routes.Login.route + "?userid={userID}") {
            com.example.week07.example2.LoginScreen(navController)
        }

        composable(route = Routes.Welcome.route + "?userid={userID}",
            arguments = listOf(navArgument(name = "userID") {
                type = NavType.StringType
                defaultValue = "'user'"
            })) {
            WelcomeScreen(
                navController, it.arguments?.getString("userID")
            )
        }

        composable(route = Routes.Register.route + "?userID={userID}&userPasswd={userPasswd}",
            arguments = listOf(navArgument(name = "userID") {
                type = NavType.StringType
                defaultValue = "user"
            }, navArgument(name = "userPasswd") {
                type = NavType.StringType
                nullable = true;
            })) {
            Register(
                navController = navController,
                userID = it.arguments?.getString("userID"),
                userPasswd = it.arguments?.getString("userPasswd")
            )
        }
    }
}

