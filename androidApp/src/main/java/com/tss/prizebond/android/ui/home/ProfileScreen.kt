package com.tss.prizebond.android.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {

    val pastelBG = Color(0xFFFFF6F5)
    val darkText = Color(0xFF523A3A)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(pastelBG)
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item {
            // Logo
            Text(
                "Heard",
                fontSize = 32.sp,
                color = Color(0xFFD8C18F),
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(20.dp))
        }

        // Profile Avatar
        item {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFA78F)),
                contentAlignment = Alignment.Center
            ) {
                Text("G", color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(16.dp))

            Text("Mithun Kumar", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = darkText)
            Text("You deserve to be heard", fontSize = 13.sp, color = Color.Gray)
            Text("evelyn.quince@example.com", fontSize = 13.sp, color = Color.Gray)

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp)
            ) {
                Text("Edit Profile")
            }

            Spacer(Modifier.height(24.dp))
        }

        // LIST ITEMS
        val items = listOf(
            "Change password",
            "Cancel Subscription (Monthly)",
            "Delete account",
            "Upgrade plan",
            "Contact us"
        )

        items(items) { text ->
            ProfileListItem(text)
            Spacer(Modifier.height(10.dp))
        }

        item {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(14.dp))
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Notifications", fontSize = 16.sp, fontWeight = FontWeight.Medium)
                Switch(checked = true, onCheckedChange = {})
            }
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
fun ProfileListItem(text: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(14.dp))
            .padding(18.dp)
            .clickable { },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Text(">", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Gray)
    }
}
