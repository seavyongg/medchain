package com.example.medchain.Feature.unauthorized.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.medchain.Feature.appcompat.DynamicPermissionDialog
import com.example.medchain.core.data.ClaimTokenRequest
import kotlin.math.sign

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSignIn(
    modifier: Modifier = Modifier,
    onNavigateTo: () -> Unit = {},
    onBackPress: () -> Unit = {},
    viewModel: SignInViewModel = hiltViewModel(),
    scannedToken: String? = null
) {
    // 1. State management
    var showCredential by remember { mutableStateOf(false) }
    val displayCredential = if (showCredential) scannedToken ?: "No credential found" else "••••••••••••••••••••••••••••••"
    val signInState by viewModel.signInState.collectAsState()
    var errorMessage by remember { mutableStateOf("") }
    LaunchedEffect(signInState) {
        when (val state = signInState) {
            is SignInState.Success -> onNavigateTo()
            is SignInState.Error -> {
                errorMessage = state.message
            }
            else -> {}
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Claim Credential") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if(errorMessage.isNotEmpty()){
                DynamicPermissionDialog(
                    title = "Sign In Failed",
                    description = errorMessage,
                    buttonText = "OK",
                    onConfirm = {
                        errorMessage = ""
                    },
                    onDismissRequest = {
                        errorMessage = ""
                    }
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
            ) {
                Text(
                    text = "User Credential Issued",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .background(Color.Gray.copy(0.1f), RoundedCornerShape(12.dp))
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = displayCredential,
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = if(showCredential) TextOverflow.Ellipsis else TextOverflow.Clip,
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyMedium
                    )

                    IconButton(onClick = { showCredential = !showCredential }) {
                        Icon(
                            imageVector = if (showCredential) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                            contentDescription = if (showCredential) "Hide" else "Show",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Button(
                    onClick = {
                        viewModel.signIn(
                            signInRequest = ClaimTokenRequest(claimToken = scannedToken ?: "")
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.7f) // Better responsiveness than wrapContent
                        .height(60.dp)
                        .clickable{

                        },
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Claim your credential",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}