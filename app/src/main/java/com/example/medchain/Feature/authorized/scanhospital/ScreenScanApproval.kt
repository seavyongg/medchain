package com.example.medchain.Feature.authorized.scanhospital

import android.Manifest
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.medchain.Feature.appcompat.DynamicPermissionDialog
import com.example.medchain.Feature.appcompat.ScanCode
import com.example.medchain.Feature.unauthorized.signin.SignInViewModel
import com.example.medchain.core.data.ConfirmInfo
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun ScreenScanHospitalQr(
    modifier: Modifier = Modifier,
    navigateTo: (String) -> Unit = {},
    onBackPress: () -> Unit = {},
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
//    LaunchedEffect(barcode) {
//        val code = barcode
//        if (code.isNullOrBlank()) return@LaunchedEffect
//        runCatching {
//            navigateTo(code)
//        }.onFailure {
//            barcode = null // Reset barcode to allow rescanning
//        }
//    }
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
            barcode = null
        }
    }
    ScanCode(
        navigateTo = {
            barcode = it
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
        //barcode not null give to show dialog
        if(barcode != null){
            DynamicPermissionDialog(
                title = "Scan Successful",
                description = "You have successfully scanned the QR code.",
                buttonText = "Continue",
                onConfirm = {
                    navigateTo(barcode ?: "")
                    barcode = null
                },
                onDismissRequest = {
                    barcode = null
                    onBackPress()
                }
            )
        }
    }
}