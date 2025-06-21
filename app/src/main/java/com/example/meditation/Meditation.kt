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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
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

    var secendtime by mutableFloatStateOf(7f)
    var mediaPlayer: MediaPlayer? = null
    var mediaPlayer2: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mediaPlayer = MediaPlayer.create(this, R.raw.bell_song)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.main_meditation_song)

        setContent {
            mediaPlayer2?.start()
            MyHome()
        }
    }

    @SuppressLint("DefaultLocale")
    @Composable
    @Preview(showSystemUi = true)

    fun MyHome() {
        //music play mate

        var currentTime by remember { mutableIntStateOf(0) }
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
            if (minutes == 21) {
                secendtime = 30f
            }
        }

// secendtime = minutes.toFloat()
        var currentRotation by remember { mutableFloatStateOf(0f) }
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
            Button(
                onClick = {
                    finish()
                    mediaPlayer?.pause()
                    mediaPlayer2?.pause()
                },
                modifier = Modifier.padding(top = 5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(.2f))
            ) {
                Text("Back", color = Color.White)
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
                Text(text = "Close Your Eye", fontSize = 25.sp, color = Color.Gray)
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 37.dp, start = 0.dp)
            ) {
                Text(
                    text = timeText,
                    fontSize = 25.sp,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 37.dp, start = 60.dp)
            ) {
                Text(
                    text = " / ${secendtime.toInt()}:00",
                    fontSize = 25.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(16.dp)
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 710.dp)
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