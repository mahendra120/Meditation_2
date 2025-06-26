package com.example.meditation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.ui.theme.customAppFontFamily

class Start : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyHome()

        }
    }

    @Composable
    @Preview(showSystemUi = true)
    fun MyHome() {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        var currentRotation by remember { mutableStateOf(0f) }
        val rotation = remember { Animatable(currentRotation) }
        LaunchedEffect(true) {
            rotation.animateTo(
                targetValue = currentRotation + 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(90000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            ) {
                currentRotation = value
            }
        }
        Box(modifier = Modifier.fillMaxSize())
        {
            Image(
                painter = painterResource(R.drawable.starpage),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = screenHeight * .04f)
            ) {
                Image(
                    painter = painterResource(R.drawable.app_logo_background),
                    contentDescription = null,
                    modifier = Modifier
                        .size(190.dp)
                        .align(Alignment.TopCenter)
                        .rotate(currentRotation)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Just 7 minutes of daily meditation,\nBrings peace, joy, and pure elevation.",
                        fontFamily = customAppFontFamily,
                        fontSize = 15.sp,
                        color = Color.White,
                        textAlign = TextAlign.End
                    )
                    Spacer(modifier = Modifier.height(40.dp)) // Top spacing
                    Button(
                        onClick = {
                            val intent = Intent(this@Start, signip::class.java)
                            startActivity(intent)
                        },
                        modifier = Modifier
                            .padding(bottom = screenHeight * .08f) // 🔹 Only 3.dp from bottom
                            .height(screenHeight * .063f)
                            .width(screenWidth * .86f),
                        border = BorderStroke(1.dp, Color.White),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                    ) {
                        Text(
                            text = "Let's Get Started",
                            fontSize = 18.sp,
                            fontFamily = customAppFontFamily
                        )
                    }
                }
            }
        }
    }
}