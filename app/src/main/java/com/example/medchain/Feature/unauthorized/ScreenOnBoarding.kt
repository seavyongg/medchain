package com.example.medchain.Feature.unauthorized

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.medchain.R

@Composable
fun ScreenOnBoarding(
    modifier: Modifier = Modifier,
    onNavigateTo : () -> Unit = {},
) {
    Surface(
        modifier = modifier.fillMaxSize()
    )
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(50.dp, alignment = androidx.compose.ui.Alignment.CenterVertically)
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = R.drawable.ic_on_boarding),
                contentDescription = "On Boarding Image",
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Start holding your health wallet with us!!" ,
                    modifier = Modifier ,
                    fontSize = 20.sp ,
                    fontWeight = FontWeight.Medium ,
                    textAlign = TextAlign.Center,
                    color = Color(MaterialTheme.colorScheme.primary.value)
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    text = "Securing and managing your record" ,
                    modifier = Modifier ,
                    fontSize = 15.sp ,
                    fontWeight = FontWeight.Medium ,
                    color = Color(Color.Gray.value)
                )
            }
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
                colors = androidx.compose.material3.ButtonDefaults.buttonColors(Color(MaterialTheme.colorScheme.primary.value))
            ) {
                Text(
                    text = "Let's Start" ,
                    modifier = Modifier ,
                    fontSize = 15.sp ,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun ScreenOnBoardingPreview() {
    ScreenOnBoarding()
}