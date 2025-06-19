package com.example.meditation

import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay

class Meditation : ComponentActivity() {

    var secendtime by mutableStateOf(7f)
    var mediaPlayer: MediaPlayer? = null
    var mediaPlayer2: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            MyHome()
        }
    }

    @SuppressLint("DefaultLocale")
    @Composable
    @Preview(showSystemUi = true)

    fun MyHome() {
        //music play mate
        mediaPlayer = MediaPlayer.create(this, R.raw.bell_song)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.main_meditation_song)

        var currentTime by remember { mutableStateOf(0) }
        val maxTime = 30 * 60

        //time show mate
        LaunchedEffect(Unit) {
            while (currentTime <= maxTime) {
                delay(1000) // Wait 1 second
                currentTime++
            }
        }

        val minutes = currentTime / 60
        val seconds = currentTime % 60
        val timeText = String.format("%d:%02d", minutes, seconds)


        if (minutes == 7 || minutes == 12 || minutes == 15 || minutes == 18 || minutes == 21) {
            mediaPlayer?.start()
            if (minutes == 7) {
                secendtime = 12f
            }
            if (minutes == 12) {
                secendtime = 15f
            }
            if (minutes == 15) {
                secendtime = 18f
            }
            if (minutes == 18) {
                secendtime = 21f
            }
        }

        LaunchedEffect(minutes, seconds) {
            // If time is between 6:58 and 7:07 (inclusive), pause the music
            if ((minutes == 6 && seconds >= 58) || (minutes == 7 && seconds <= 7)||()||()) {
                mediaPlayer2?.pause()
            } else {
                if (mediaPlayer2?.isPlaying == false) {
                    mediaPlayer2?.start()
                }
            }
        }


// secendtime = minutes.toFloat()
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            IconButton(
                onClick = { finish() },
                modifier = Modifier.padding(top = 80.dp, start = 5.dp)
            )
            {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp)
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.app_logo_background),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier
                        .size(290.dp)
                        .rotate(currentRotation)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 62.dp, start = 5.dp)
            ) {
                Text(
                    text = timeText,
                    fontSize = 25.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 62.dp, end = 5.dp)
            ) {
                Text(
                    text = "${secendtime.toInt()}:00",
                    fontSize = 25.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 630.dp)
                    .align(Alignment.BottomCenter)
            ) {
                AnimatedPreloader()
            }
        }
    }

    @Composable
    fun AnimatedPreloader(modifier: Modifier = Modifier) {
        val preloaderLottieComposition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                R.raw.music_1
            )
        )

        val preloaderProgress by animateLottieCompositionAsState(
            preloaderLottieComposition,
            iterations = LottieConstants.IterateForever,
            isPlaying = true
        )


        LottieAnimation(
            composition = preloaderLottieComposition,
            progress = preloaderProgress,
            modifier = modifier
        )
    }
}