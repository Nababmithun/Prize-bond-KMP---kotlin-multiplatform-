package com.tss.prizebond.android.ui.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.tss.prizebond.android.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen() {
    var tab by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = tab == 0,
                    onClick = { tab = 0 },
                    label = { Text("Dashboard") },

                    //  Use your drawable icon here
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_add_home_24),
                            contentDescription = "Dashboard"
                        )
                    }
                )

                NavigationBarItem(
                    selected = tab == 1,
                    onClick = { tab = 1 },
                    label = { Text("Profile") },

                    //  Your second drawable icon
                    icon = {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_batch_prediction_24),
                            contentDescription = "Profile"
                        )
                    }
                )
            }
        }
    ) { padding ->
        when (tab) {
            0 -> DashboardScreen()
            1 -> ProfileScreen(modifier = Modifier.padding(padding))
        }
    }
}
