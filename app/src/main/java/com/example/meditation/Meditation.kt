package com.example.meditation

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.meditation.ui.theme.customAppFontFamily
import kotlinx.coroutines.delay


class Meditation : ComponentActivity() {
    val sp by lazy {
        getSharedPreferences("lecture", MODE_PRIVATE)
    }
    var secendtime by mutableFloatStateOf(7f)
    var mediaPlayer: MediaPlayer? = null
    var mediaPlayer2: MediaPlayer? = null
    var mediaPlayer3: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mediaPlayer = MediaPlayer.create(this, R.raw.bell_song)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.main_meditation_song)
        mediaPlayer3 = MediaPlayer.create(this, R.raw.meditationttt)

        setContent {
            mediaPlayer3?.start()
            MyHome()
        }
    }

    @SuppressLint("DefaultLocale", "CommitPrefEdits")
    @Composable
    @Preview(showSystemUi = true)

    fun MyHome() {
        //music play mate
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp

        var currentTime by remember { mutableIntStateOf(0) }
        var lastMinute by remember { mutableIntStateOf(0) }
        val maxTime = 30 * 60
        var fristsong by remember { mutableIntStateOf(37) }

        if (fristsong <= 0) {
            mediaPlayer2?.pause()
        } else {
            mediaPlayer2?.start()
        }

        // time show mate
        if (fristsong <= 0) {
            LaunchedEffect(Unit) {
                while (currentTime <= maxTime) {
                    delay(1000) // Wait 1 second
                    currentTime++
                }
            }
        }

        LaunchedEffect(Unit)
        {
            while (fristsong >= 0) {
                delay(1000) // Wait 1 second
                fristsong--
            }
        }

        val minutes = currentTime / 60
        val seconds = currentTime % 60
        val timeText = String.format("%d:%02d", minutes, seconds)

        if (lastMinute != minutes) {
            lastMinute = minutes
            sp?.edit()?.apply {
                this.putInt("time", sp?.getInt("time", 0)?.plus(1) ?: 0)
                this.apply()
            }
        }

        if (minutes == 7) {
            secendtime = 12f
            mediaPlayer?.start()
        }
        if (minutes == 12) {
            secendtime = 15f
            mediaPlayer?.start()
        }
        if (minutes == 15) {
            secendtime = 18f
            mediaPlayer?.start()
        }
        if (minutes == 18) {
            secendtime = 21f
            mediaPlayer?.start()
        }
        if (minutes == 21) {
            secendtime = 30f
            mediaPlayer?.start()
        }

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
            IconButton(
                onClick = {
                    mediaPlayer?.pause()
                    mediaPlayer2?.pause()
                    mediaPlayer3?.pause()
                    val intent = Intent(this@Meditation, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                modifier = Modifier.padding(top = 45.dp),
                colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Transparent),
            )
            {
                Icon(
                    Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier.size(33.dp),
                    tint = Color.White
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                if (fristsong <= 0) {
                    Image(
                        painter = painterResource(R.drawable.app_logo_background),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Color.White),
                        modifier = Modifier
                            .size(290.dp)
                            .rotate(currentRotation)
                    )
                }
                if (fristsong <= 0) {
                    Text(
                        text = "Close Your Eye",
                        fontSize = 30.sp,
                        color = Color.Gray,
                        fontFamily = customAppFontFamily
                    )
                } else {
                    Column(modifier = Modifier.padding(start = 3.dp)) {
                        Text(
                            text = "Please Lister",
                            fontSize = 44.sp,
                            color = Color.White,
                            fontFamily = customAppFontFamily
                        )
                        Text(
                            text = "To The",
                            fontSize = 44.sp,
                            color = Color.White.copy(alpha = 0.9f),
                            fontFamily = customAppFontFamily
                        )
                        Text(
                            text = "Instructions",
                            fontSize = 44.sp,
                            color = Color.White.copy(alpha = 0.7f),
                            fontFamily = customAppFontFamily
                        )
                    }
                }
            }
            if (fristsong <= 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = screenWidth * .23f, end = 90.dp)
                ) {
                    Text(
                        text = timeText,
                        fontSize = 25.sp,
                        color = Color.White,
                        modifier = Modifier
                    )
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = screenWidth * .23f, end = 15.dp)
                ) {
                    Text(
                        text = "  / ${secendtime.toInt()}:00",
                        fontSize = 25.sp,
                        color = Color.Gray,
                        modifier = Modifier
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = screenWidth * .21f, end = 32.dp)
                ) {
                    if (fristsong >= 10) {
                        Text(
                            text = "-  00:${fristsong}",
                            fontSize = 25.sp,
                            color = Color.White,
                            modifier = Modifier
                        )
                    } else {
                        Text(
                            text = "-  00:0${fristsong}",
                            fontSize = 25.sp,
                            color = Color.White,
                            modifier = Modifier
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = screenHeight * 0.85f)
                    .size(screenWidth * 0.6f)
                    .align(Alignment.BottomCenter)
            ) {
                if (fristsong <= 0) {
                    AnimatedPreloader()
                }
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