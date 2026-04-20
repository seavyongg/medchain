package com.example.medchain.Feature.unauthorized.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenSignIn(
    modifier : Modifier = Modifier,
    onNavigateTo : () -> Unit = {},
    onBackPress: () -> Unit = {},
) {
    var showCredential by remember { mutableStateOf(false) }
    var credential by remember { mutableStateOf("Your credential has been issued successfully. Please click the button below to claim your credential and start managing your health records securely.") }
    val dots = "••••••••••••••••••••••••••••••••••••••••••"
    val displayCredential = if (showCredential) credential else dots

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
                    titleContentColor = Color(MaterialTheme.colorScheme.primary.value),
                    navigationIconContentColor = Color(MaterialTheme.colorScheme.primary.value)
                )
            )
        }
    )
    { paddingValues ->
        Surface(modifier = modifier
            .fillMaxSize()
            .padding(paddingValues))
        {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterVertically)
            ) {
                Text(
                    text = "User Credential Issued" ,
                    modifier = Modifier ,
                    fontSize = 20.sp ,
                    fontWeight = FontWeight.Medium ,
                    color = Color(MaterialTheme.colorScheme.primary.value)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .background(Color.Gray.copy(0.2f), RoundedCornerShape(10.dp))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = displayCredential,
                        modifier = Modifier.weight(1f),
                        maxLines = 1,
                        overflow = if(showCredential) TextOverflow.Ellipsis else TextOverflow.Clip, // The native way to do it
                        color = Color.Black,

                    )
                   if(showCredential){
                            IconButton(onClick = { showCredential = false }) {
                                Icon(Icons.Default.Visibility, contentDescription = "Hide Credential")
                            }
                   } else {
                          IconButton(onClick = { showCredential = true }) {
                            Icon(Icons.Default.VisibilityOff, contentDescription = "Show Credential")
                          }
                   }
                }
                Button(
                    onClick = onNavigateTo,
                    modifier = Modifier
                        .wrapContentWidth()
                        .height(80.dp)
                        .padding(
                            vertical = 10.dp
                        ),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(MaterialTheme.colorScheme.primary.value))
                ) {
                    Text(
                        text = "Claim your credential" ,
                        modifier = Modifier ,
                        fontSize = 15.sp ,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenClaimTokenPreview() {
    ScreenSignIn()
}