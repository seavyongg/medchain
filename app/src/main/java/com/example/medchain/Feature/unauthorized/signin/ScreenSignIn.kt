package com.example.medchain.Feature.unauthorized.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        Surface(modifier = modifier.fillMaxSize().padding(paddingValues))
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
                TextField(
                    value = "Your credential has been issued successfully. Please click the button below to claim your credential and access your personalized dashboard.",
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),

                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "View"
                        )
                    },

                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor =  Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,

                        focusedContainerColor =  Color.Gray.copy(0.2f),
                        unfocusedContainerColor =  Color.Gray.copy(0.2f),
                        disabledContainerColor =  Color.Gray.copy(0.2f)
                    ),
                    shape = RoundedCornerShape(10.dp),
                    maxLines = 2,

                )
                Button(
                    onClick = onNavigateTo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(
                            horizontal = 100.dp,
                            vertical = 10.dp
                        ),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(Color(MaterialTheme.colorScheme.primary.value))
                ) {
                    Text(
                        text = "Claim you credential" ,
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