package com.example.medchain.Feature.appcompat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.medchain.ui.theme.MedChainTheme

@Composable
fun DynamicPermissionDialog(
    modifier : Modifier = Modifier,
    title: String,
    description: String,
    buttonText: String,
    onConfirm: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = {
            onDismissRequest()
        },
        properties = DialogProperties(dismissOnClickOutside = true)

    ) {
           Card(
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(8.dp),
               shape = RoundedCornerShape(16.dp),
               colors = CardDefaults.cardColors(containerColor = Color.White),
               elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
           ) {
               IconButton(onClick = onDismissRequest) {
                   Icon(
                       imageVector = Icons.Default.Close ,
                       contentDescription = "Close Dialog" ,
                       tint = MaterialTheme.colorScheme.primary
                   )
               }
               Column(
                   modifier = Modifier
                       .padding(horizontal = 16.dp, vertical = 8.dp)
                       .fillMaxWidth() ,
                   verticalArrangement = Arrangement.spacedBy(8.dp)
               ) {
                   // Dynamic Title
                   Text(
                       text = title ,
                       fontStyle = MaterialTheme.typography.labelLarge.fontStyle ,
                       fontSize = MaterialTheme.typography.labelLarge.fontSize ,
                       fontWeight = FontWeight.Medium ,
                       color = MaterialTheme.colorScheme.primary ,
                       textAlign = TextAlign.Start ,
                   )

                   // Dynamic Description
                   Text(
                       text = description ,
                       fontStyle = MaterialTheme.typography.labelSmall.fontStyle ,
                       fontSize = MaterialTheme.typography.labelSmall.fontSize ,
                       color = Color.LightGray ,
                       fontWeight = FontWeight.Medium ,
                       lineHeight = 15.sp
                   )
                   Spacer(modifier = Modifier.height(8.dp))
                   // Dynamic Action Button
                   Button(
                       onClick = onConfirm ,
                       modifier = Modifier
                           .fillMaxWidth()
                           .wrapContentHeight(align = Alignment.CenterVertically) ,
                       shape = RoundedCornerShape(14.dp) ,
                       colors = ButtonDefaults.buttonColors(
                           containerColor = MaterialTheme.colorScheme.primary
                       )
                   ) {
                       Text(
                           text = buttonText ,
                           fontSize = MaterialTheme.typography.labelLarge.fontSize ,
                           color = Color.White ,
                           fontWeight = FontWeight.Medium
                       )
                   }
               }
           }
    }
}

@Preview(showBackground = true)
@Composable
fun DynamicPermissionDialogPreview() {
    MedChainTheme{
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            contentWindowInsets = WindowInsets(0.dp)
        ) { innerPadding ->
            DynamicPermissionDialog(
                modifier = Modifier.padding(innerPadding),
                title = "Permission Required",
                description = "To provide you with a personalized experience, we need access to your health data. Please grant permission to continue.",
                buttonText = "Grant Permission",
                onConfirm = { /* Handle permission logic */ },
                onDismissRequest = { /* Handle dismiss logic */ }
            )
        }
    }
}