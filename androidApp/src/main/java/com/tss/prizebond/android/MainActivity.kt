package com.tss.prizebond.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.tss.prizebond.di.AuthFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val authVm = AuthFactory.createAuthViewModel()

        setContent {
            PrizeBondApp(authVm = authVm)
        }
    }
}
