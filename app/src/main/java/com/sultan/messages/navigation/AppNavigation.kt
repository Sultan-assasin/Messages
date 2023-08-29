package com.sultan.messages.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sultan.messages.Activity.MainScreen
import com.sultan.messages.Activity.MessageScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.MainScreen.name) {

        composable(Screens.MainScreen.name) {
            MainScreen(navController)
        }
        composable(Screens.MessageScreen.name) {
            MessageScreen()
        }
    }
}