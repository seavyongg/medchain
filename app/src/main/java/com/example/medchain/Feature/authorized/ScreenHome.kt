package com.example.medchain.Feature.authorized

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medchain.Feature.appcompat.AppBottomNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenHome(
    modifier: Modifier = Modifier,
    navigateTo: (String) -> Unit = {},
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            AppBottomNavigation(
                navigateToItem = navigateTo
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp)
        ) {
            // Header: User Profile
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color.LightGray)
                    ) {
                        // Placeholder for User Image
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "LA Seavyong",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            // Total Access Card
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                        .padding(24.dp)
                ) {
                    Column {
                        Text(text = "Total access", color = Color.LightGray, fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "12 hospital",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Currently Hospital Access Section
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Currently Hospital access",
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(16.dp))
                HospitalHorizontalList()
            }

            // Latest Records Section
            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    text = "Lastest Records",
                    fontSize = 18.sp,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(4) { // Mocking 4 items
                RecordItem()
            }
        }
    }
}

@Composable
fun HospitalHorizontalList() {
    val hospitals = listOf("Calmette", "Soviet", "Rumdul Clinic", "Sen Sok", "Orchid")
    LazyRow(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        items(hospitals) { name ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(65.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = name, fontSize = 12.sp, color = Color.LightGray)
            }
        }
    }
}

@Composable
fun RecordItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "Calmette", fontWeight = FontWeight.Medium)
                Text(text = "Today | 12am", color = Color.LightGray, fontSize = 12.sp)
            }
        }
    }
}


data class NavigationItem(val title: String, val icon: ImageVector)

@Preview(showBackground = true)
@Composable
fun ScreenHomePreview() {
    ScreenHome()
}