package com.example.medchain.Feature.authorized

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QRScannerScreen(
    onBackPress: () -> Unit = {},
) {
    Scaffold(
        // Set containerColor to Transparent so the Scaffold doesn't block the background
        containerColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                // This makes the TopAppBar background invisible
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                    scrolledContainerColor = Color.Transparent
                ),
                // Important: This allows the content to draw behind the top bar
                windowInsets = WindowInsets(0.dp)
            )
        }
    ) { paddingValues ->
        // The background color needs to be applied to the main container
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF121212))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues), // Respects top bar height
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Scan your QR Code",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(40.dp))

                // The QR Focus Area
                Box(
                    modifier = Modifier.size(280.dp),
                    contentAlignment = Alignment.Center
                ) {
                    QRScannerOverlay(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.White,
                        strokeWidth = 4.dp,
                        lineLength = 40.dp
                    )

                    // QR Code Placeholder
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .background(Color.White)
                    )
                }
            }
        }
    }
}

@Composable
fun QRScannerOverlay(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    strokeWidth: Dp = 4.dp,
    lineLength: Dp = 40.dp
) {
    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val strokePx = strokeWidth.toPx()
        val linePx = lineLength.toPx()

        // Top Left
        drawLine(color, Offset(0f, 0f), Offset(linePx, 0f), strokePx, StrokeCap.Round)
        drawLine(color, Offset(0f, 0f), Offset(0f, linePx), strokePx, StrokeCap.Round)

        // Top Right
        drawLine(color, Offset(width, 0f), Offset(width - linePx, 0f), strokePx, StrokeCap.Round)
        drawLine(color, Offset(width, 0f), Offset(width, linePx), strokePx, StrokeCap.Round)

        // Bottom Left
        drawLine(color, Offset(0f, height), Offset(linePx, height), strokePx, StrokeCap.Round)
        drawLine(color, Offset(0f, height), Offset(0f, height - linePx), strokePx, StrokeCap.Round)

        // Bottom Right
        drawLine(color, Offset(width, height), Offset(width - linePx, height), strokePx, StrokeCap.Round)
        drawLine(color, Offset(width, height), Offset(width, height - linePx), strokePx, StrokeCap.Round)
    }
}

@Preview(showBackground = true)
@Composable
fun QRScannerScreenPreview() {
    MaterialTheme {
        QRScannerScreen()
    }
}