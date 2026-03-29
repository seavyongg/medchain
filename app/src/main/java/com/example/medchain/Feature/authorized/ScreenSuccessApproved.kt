package com.example.medchain.Feature.authorized

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medchain.ui.theme.MedChainTheme


@Composable
fun ApprovalSuccessScreen(
    modifier: Modifier = Modifier,
    onBackHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background.copy(alpha = 1f))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Title
        Text(
            text = "Approval Successfully",
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Subtitle/Description
        Text(
            text = "You have successfully granted permission to access your health information.",
            fontSize = 16.sp,
            color = Color.LightGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 20.dp),
            lineHeight = 22.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Success Icon with Circle Background
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(color =  MaterialTheme.colorScheme.primary.copy(alpha = 0.1f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Success",
                tint = Color.Green,
                modifier = Modifier.size(50.dp)
            )
        }

        Spacer(modifier = Modifier.height(64.dp))

        // Back Home Button
        Button(
            onClick = onBackHome,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Back home",
                fontSize = MaterialTheme.typography.labelLarge.fontSize,
                fontWeight = FontWeight.Normal,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ApprovalSuccessScreenPreview() {
    MedChainTheme {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background
        ) { paddingValues ->
            ApprovalSuccessScreen(
                modifier = Modifier.padding(paddingValues),
                onBackHome = { }
            )
        }
    }
}