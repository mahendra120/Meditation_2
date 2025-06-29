package com.example.meditation

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mediaPlayer = MediaPlayer.create(this, R.raw.bell_song)
        mediaPlayer2 = MediaPlayer.create(this, R.raw.main_meditation_song)

        setContent {
            mediaPlayer2?.start()
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

        // time show mate
        LaunchedEffect(Unit) {
            while (currentTime <= maxTime) {
                delay(1000) // Wait 1 second
                currentTime++
                Log.d("333333", "MyHome: ${currentTime}")
            }
        }

        val minutes = currentTime / 60
        val seconds = currentTime % 60
        val timeText = String.format("%d:%02d", minutes, seconds)

        // var min = 0
        // LaunchedEffect(Unit){
        //    while (isActive){
        //        delay(60000)
        //        min++
        //   }
        //  }

        if (lastMinute != minutes) {
            lastMinute = minutes
            sp?.edit()?.apply {
                this.putInt("time", sp?.getInt("time", 0)?.plus(1) ?: 0)
                this.apply()
            }
        }



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
                    mediaPlayer?.pause()
                    mediaPlayer2?.pause()
                    val intent = Intent(this@Meditation, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                },
                modifier = Modifier.padding(top = 30.dp, start = 5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(.05.dp, color = Color.White)
            )
            {
                Text(
                    text = "<",
                    fontSize = 15.sp,
                    fontFamily = customAppFontFamily,
                    color = Color.White
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
                Text(text = "Close Your Eye", fontSize = 25.sp, color = Color.Gray)
            }
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = screenWidth * .23f, start = 15.dp)
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
                    .align(Alignment.BottomStart)
                    .padding(bottom = screenWidth * .23f, start = 70.dp)
            ) {
                Text(
                    text = " / ${secendtime.toInt()}:00",
                    fontSize = 25.sp,
                    color = Color.Gray,
                    modifier = Modifier
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = screenHeight * 0.85f)
                    .size(screenWidth * 0.6f)
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