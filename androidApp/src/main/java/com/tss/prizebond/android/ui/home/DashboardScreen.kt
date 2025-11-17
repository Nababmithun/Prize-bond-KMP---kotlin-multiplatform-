package com.tss.prizebond.android.ui.home

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tss.prizebond.android.R
import java.time.*

data class Feature(val icon: Int, val title: String)
data class Journal(val title: String, val subtitle: String, val date: String)

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun DashboardScreen() {

    val activity = LocalContext.current as Activity
    val window = calculateWindowSizeClass(activity)

    val bg = Color(0xFFFFF7F5)
    val pink = Color(0xFFE5C6CB)
    val deepPink = Color(0xFFC28A9A)
    val grayText = Color(0xFF8A7A7A)
    val darkText = Color(0xFF523A3A)

    val features = listOf(
        Feature(R.drawable.baseline_add_home_24, "Reprocess"),
        Feature(R.drawable.baseline_batch_prediction_24, "TalkBack"),
        Feature(R.drawable.baseline_add_home_24, "Soulspace"),
        Feature(R.drawable.baseline_batch_prediction_24, "Kindmind"),
        Feature(R.drawable.baseline_add_home_24, "Boundary tracker"),
        Feature(R.drawable.baseline_batch_prediction_24, "Morning mantra"),
        Feature(R.drawable.baseline_add_home_24, "Truth teller"),
        Feature(R.drawable.baseline_batch_prediction_24, "Inner letters"),
        Feature(R.drawable.baseline_add_home_24, "Connectivity")
    )

    val entries = listOf(
        Journal("Journal entry", "I reflected on today’s feelings", "1st May"),
        Journal("Reflection", "Morning thoughts and feelings", "16 May"),
        Journal("Inner thoughts", "Expressed emotions in depth", "13 May")
    )

    // Calendar state
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(bg)
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        item { Spacer(Modifier.height(10.dp)) }
        item { HeaderSectionUI() }
        item { Spacer(Modifier.height(20.dp)) }

        // Welcome text
        item {
            Column(Modifier.fillMaxWidth()) {
                Text("Welcome, Syndrela",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = darkText
                )
                Text("You are so strong.",
                    fontSize = 15.sp,
                    color = grayText
                )
            }
            Spacer(Modifier.height(20.dp))
        }

        // ⭐ GRID — EXACT screenshot style
        item {
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                maxItemsInEachRow = 3,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                features.forEach {
                    FeatureItemUI(it)
                }
            }
            Spacer(Modifier.height(24.dp))
        }

        // Add new entry button
        item {
            Button(
                onClick = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = deepPink)
            ) {
                Text("Add New Entry", color = Color.White, fontSize = 16.sp)
            }
            Spacer(Modifier.height(20.dp))
        }

        // ⭐ MONTH HEADER with arrows
        item {
            CalendarMonthHeader(
                currentMonth = currentMonth,
                onPrev = { currentMonth = currentMonth.minusMonths(1) },
                onNext = { currentMonth = currentMonth.plusMonths(1) }
            )
            Spacer(Modifier.height(5.dp))
        }

        // ⭐ CALENDAR GRID
        item {
            MonthlyCalendar(currentMonth)
            Spacer(Modifier.height(20.dp))
        }

        // ⭐ ALL ENTRIES HEADER
        item {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("All Entries", fontSize = 22.sp, fontWeight = FontWeight.Bold, color = darkText)

                Box(
                    modifier = Modifier
                        .background(Color.White, RoundedCornerShape(20.dp))
                        .padding(horizontal = 14.dp, vertical = 6.dp)
                ) {
                    Text("Filter", fontSize = 13.sp, color = grayText)
                }
            }
            Spacer(Modifier.height(14.dp))
        }

        // Entries list
        items(entries) { entry ->
            JournalCard(entry)
            Spacer(Modifier.height(14.dp))
        }

        item { Spacer(Modifier.height(40.dp)) }
    }
}

// -------------------------------------------------------------------------------------------------------------
// HEADER UI
// -------------------------------------------------------------------------------------------------------------

@Composable
fun HeaderSectionUI() {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text("Heal", color = Color(0xFFD8C18F), fontSize = 30.sp, fontWeight = FontWeight.Bold)

        Box(
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(horizontal = 16.dp, vertical = 6.dp)
        ) {
            Text("QUICK EXIT >", fontSize = 12.sp, color = Color(0xFF523A3A))
        }
    }
}

// -------------------------------------------------------------------------------------------------------------
// FEATURE ITEM UI
// -------------------------------------------------------------------------------------------------------------

@Composable
fun FeatureItemUI(feature: Feature) {
    Column(
        modifier = Modifier
            .width(94.dp)
            .height(94.dp)
            .background(Color.White, RoundedCornerShape(24.dp))
            .shadow(4.dp, RoundedCornerShape(24.dp))
            .clickable { }
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painterResource(feature.icon),
            contentDescription = feature.title,
            modifier = Modifier.size(38.dp),
            tint = Color.Unspecified
        )
        Spacer(Modifier.height(6.dp))
        Text(feature.title, fontSize = 12.sp, color = Color(0xFF523A3A))
    }
}

// -------------------------------------------------------------------------------------------------------------
// CALENDAR HEADER
// -------------------------------------------------------------------------------------------------------------

@Composable
fun CalendarMonthHeader(currentMonth: YearMonth, onPrev: () -> Unit, onNext: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(20.dp))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
       /* Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Prev", Modifier.clickable { onPrev() })
        Text("${currentMonth.month} ${currentMonth.year}", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Next", Modifier.clickable { onNext() })*/
    }
}

// -------------------------------------------------------------------------------------------------------------
// MONTHLY CALENDAR (Simple Version)
// -------------------------------------------------------------------------------------------------------------

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MonthlyCalendar(month: YearMonth) {

    val firstDay = month.atDay(1)
    val lastDay = month.lengthOfMonth()
    val firstDayOfWeek = firstDay.dayOfWeek.value % 7

    val days = (1..lastDay).toList()

    FlowRow(
        maxItemsInEachRow = 7,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        repeat(firstDayOfWeek) {
            Box(modifier = Modifier.size(34.dp))
        }

        days.forEach {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .background(Color.White, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("$it", fontSize = 12.sp)
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------------------
// JOURNAL CARD
// -------------------------------------------------------------------------------------------------------------

@Composable
fun JournalCard(journal: Journal) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(22.dp))
            .padding(16.dp)
    ) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(journal.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
            Text(journal.date, fontSize = 12.sp, color = Color.Gray)
        }
        Spacer(Modifier.height(6.dp))
        Text(journal.subtitle, fontSize = 13.sp, color = Color.Gray)
    }
}
