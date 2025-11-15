package com.tss.prizebond.android

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.tss.prizebond.data.AuthViewModel

@Composable
fun PrizeBondApp(authVm: AuthViewModel) {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            AuthScreen(authVm = authVm)
        }
    }
}

@Composable
fun AuthScreen(authVm: AuthViewModel) {
    var isLogin by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TabRow(selectedTabIndex = if (isLogin) 0 else 1) {
            Tab(
                selected = isLogin,
                onClick = { isLogin = true },
                text = { Text("Login") }
            )
            Tab(
                selected = !isLogin,
                onClick = { isLogin = false },
                text = { Text("Register") }
            )
        }

        Spacer(Modifier.height(16.dp))

        if (isLogin) {
            LoginForm(authVm)
        } else {
            RegisterForm(authVm)
        }
    }
}

@Composable
fun LoginForm(authVm: AuthViewModel) {
    val state by authVm.loginState.collectAsState()

    var email by remember { mutableStateOf("go@gmail.com") }
    var password by remember { mutableStateOf("12345678") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { authVm.login(email, password) },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (state.isLoading) "Logging in..." else "Login")
        }

        Spacer(Modifier.height(16.dp))

        state.error?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
        state.user?.let { user ->
            Text("Logged in as: ${user.name} (${user.email})")
        }
    }
}

@Composable
fun RegisterForm(authVm: AuthViewModel) {
    val state by authVm.registerState.collectAsState()

    var name by remember { mutableStateOf("gooooooooooo") }
    var email by remember { mutableStateOf("ok@gmail.com") }
    var password by remember { mutableStateOf("12345678") }
    var confirm by remember { mutableStateOf("12345678") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = confirm,
            onValueChange = { confirm = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { authVm.register(name, email, password, confirm) },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (state.isLoading) "Creating..." else "Register")
        }

        Spacer(Modifier.height(16.dp))

        state.error?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
        state.user?.let { user ->
            Text("Registered: ${user.name} (id=${user.id})")
        }
    }
}