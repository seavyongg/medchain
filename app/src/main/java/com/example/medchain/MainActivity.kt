package com.example.medchain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.medchain.Feature.unauthorized.ScreenClaimToken
import com.example.medchain.ui.theme.MedChainTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // 1. Install splash screen BEFORE calling super
        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 2. State to control when to remove splash
        var keepSplash by mutableStateOf(true)

        // 3. Keep splash screen visible while needed
        splashScreen.setKeepOnScreenCondition { keepSplash }

        setContent {
            MedChainTheme {
                // Launch coroutine to hide splash after delay
                LaunchedEffect(Unit) {
                    kotlinx.coroutines.delay(1000) // 1 second
                    keepSplash = false
                }

                // Main Compose content
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScreenClaimToken(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}