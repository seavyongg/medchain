package com.example.medchain

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.medchain.core.network.interceptor.NetworkConnectionInterceptor
import com.example.medchain.graphs.AppNavigation
import com.example.medchain.ui.theme.MedChainTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
                    if ( !NetworkConnectionInterceptor(this).isConnected() ) {
                        ScreenNoInternet()
                    } else {
                        AppNavigation(
                            navController = rememberNavController(),
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}