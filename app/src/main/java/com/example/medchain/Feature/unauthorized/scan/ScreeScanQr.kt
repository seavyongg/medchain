package com.example.medchain.Feature.unauthorized.scan

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.medchain.Feature.appcompat.DynamicPermissionDialog
import com.example.medchain.Feature.appcompat.ScanCode
import com.example.medchain.Feature.unauthorized.signin.SignInViewModel
import com.example.medchain.core.data.ConfirmInfo
import com.google.accompanist.permissions.ExperimentalPermissionsApi
@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class) // Opt-in to use experimental permissions API
@Composable
fun ScreenScanAuth(
    modifier: Modifier = Modifier,
    navigateTo: (confirmInfo: ConfirmInfo) -> Unit = {},
    onBackPress: () -> Unit = {},
    viewModel: SignInViewModel = hiltViewModel()
) {

    // State to hold the scanned barcode value, saved across recompositions
    var barcode by rememberSaveable { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    LaunchedEffect(Unit) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }
    LaunchedEffect(barcode) {
        val code = barcode
        if (code.isNullOrBlank()) return@LaunchedEffect
        runCatching {
            val confirmInfo = ConfirmInfo(code)
            navigateTo(confirmInfo)
        }.onFailure {
            errorMessage = viewModel.onTokenChanged(code)
            barcode = null // Reset barcode to allow rescanning
        }
    }
    if(errorMessage != null){
        DynamicPermissionDialog(
            title = "Scan Failed",
            description = errorMessage ?: "Unknown error",
            buttonText = "Try Again",
            onConfirm = {
                errorMessage = null
                barcode = null
                onBackPress()
            },
            onDismissRequest = {
                errorMessage = null
                barcode = null
                onBackPress()
            }
        )
    }
    DisposableEffect(Unit) {
        onDispose {
            //clean up if needed when the composable is removed from the composition
            viewModel.resetState()
        }
    }
    ScanCode(
        navigateTo = {
            barcode = it
            val confirmInfo = ConfirmInfo(it)
            viewModel.token = it // Update the ViewModel's token variable
            viewModel.onTokenChanged(it)
            navigateTo(confirmInfo)
        },
    )
    // Check if a barcode has been scanned
    Scaffold(
        modifier = modifier.fillMaxSize().background(Color.Transparent),
        containerColor = Color.Transparent,
        topBar = {
            androidx.compose.material3.TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    Icon(
                        imageVector = androidx.compose.material.icons.Icons.Default.Cancel,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.clickable(onClick = onBackPress).padding(horizontal = 8.dp).size(32.dp)
                    )
                },
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ){
            Text(
                text = "Align the QR code within the frame to scan",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}
@Preview (showBackground = true)
@Composable
fun ScreenScanAuthPreview() {
    ScreenScanAuth(
        onBackPress = {},
        navigateTo = {}
    )
}