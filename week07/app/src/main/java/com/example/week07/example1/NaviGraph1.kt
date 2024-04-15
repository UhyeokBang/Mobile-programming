package com.example.week07.example1

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


sealed class Routes(val route: String) {
    object Home : Routes("Home")
    object A : Routes("A")
    object B : Routes("B")
    object C : Routes("C")
    object D : Routes("D")
}

@SuppressLint("RestrictedApi", "StateFlowValueCalledInComposition")
@Composable
fun NavGraph1(navController: NavHostController) {

    NavHost(navController=navController, startDestination = Routes.Home.route){

        composable(route=Routes.Home.route){
//            navController.currentBackStack.value.forEachIndexed { index, navBackStackEntry ->
//                Log.d("NavBackStack", "$index : ${navBackStackEntry.destination.route}")
//            }
            HomeScreen1(navController)
        }

        composable(route=Routes.A.route){
            ScreenA1(navController)
        }

        composable(route=Routes.B.route){
            ScreenB1(navController)
        }

        composable(route=Routes.C.route){
            ScreenC1(navController)
        }

        composable(route=Routes.D.route){
            ScreenD1(navController)
        }
    }

}