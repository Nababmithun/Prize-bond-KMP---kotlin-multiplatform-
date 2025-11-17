package com.tss.prizebond.android.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.tss.prizebond.android.ui.home.HomeScreen
import com.tss.prizebond.android.ui.auth.LoginScreen
import com.tss.prizebond.android.ui.auth.RegisterScreen
import com.tss.prizebond.android.ui.splash.SplashScreen
import com.tss.prizebond.data.AuthViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    authVm: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(
                onFinished = {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable("login") {
            LoginScreen(
                authVm = authVm,
                onLoginSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate("register")
                }
            )
        }

        composable("register") {
            RegisterScreen(
                authVm = authVm,
                onRegisterSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                },
                onBackToLogin = {
                    navController.popBackStack("login", false)
                }
            )
        }

        composable("home") {
            HomeScreen()
        }
    }
}