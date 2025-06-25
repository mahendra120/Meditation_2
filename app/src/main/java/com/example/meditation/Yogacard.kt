package com.example.meditation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class Yogacard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.background(color = Color.Black)) {
                MainScreen(onBack = {var intent = Intent(this@Yogacard,MainActivity::class.java)
                startActivity(intent)})
            }
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun MainScreen(onBack : () ->Unit) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp

    val context = LocalContext.current
    val videoUrl = "https://files.catbox.moe/o1w88b.mp4"

    val exoPlayer = ExoPlayer.Builder(context).build().apply {
        setMediaItem(MediaItem.fromUri(videoUrl))
        prepare()
        playWhenReady = true
    }
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(factory = {
        PlayerView(context).apply {
            player = exoPlayer
        }
    }, modifier = Modifier.fillMaxSize())

    Button(
        onClick = onBack,
        modifier = Modifier.padding(top =  screenHeight * .03f),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(.2f))
    ) {
        Text("Back", color = Color.White, fontSize = 16.sp)
    }
}
