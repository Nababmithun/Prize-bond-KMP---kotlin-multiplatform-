package com.tss.prizebond.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.tss.prizebond.android.nav.AppNavHost
import com.tss.prizebond.di.AuthFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create KMP shared ViewModel (Android version)
        val authVm = AuthFactory.createAuthViewModel()

        setContent {
            PrizeBondRoot(authVm = authVm)
        }
    }
}

@Composable
fun PrizeBondRoot(authVm: com.tss.prizebond.data.AuthViewModel) {
    val navController = rememberNavController()

    MaterialTheme {
        AppNavHost(
            navController = navController,
            authVm = authVm
        )
    }
}
