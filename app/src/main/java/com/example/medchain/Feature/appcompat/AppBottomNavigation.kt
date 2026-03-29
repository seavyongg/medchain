package com.example.medchain.Feature.appcompat

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medchain.Feature.authorized.NavigationItem

@Composable
fun AppBottomNavigation() {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {
        val items = listOf(
            NavigationItem("Home", Icons.Default.Home),
            NavigationItem("History", Icons.Default.DateRange),
            NavigationItem("Scan Access", Icons.Default.QrCodeScanner),
            NavigationItem("Account", Icons.Default.Menu)
        )
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == 0 ,
                onClick = {} ,
                icon = { Icon(item.icon, contentDescription = item.title) } ,
                label = { Text(item.title, fontSize = 10.sp) } ,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                )
            )
        }
    }
}