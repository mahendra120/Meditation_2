package com.example.meditation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.core.net.toUri

class Yogacard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val videoUrl = "https://media.w3.org/2010/05/sintel/trailer.mp4"
            VideoPlayerFromUrl(videoUrl)
            Box(modifier = Modifier.fillMaxSize())
            {
                Button(
                    onClick = { finish() }, modifier = Modifier.padding(top = 5.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(.3f))
                )
                {
                    Text(text = "Back", color = Color.White)
                }
            }
        }
    }

    @Composable
    fun VideoPlayerFromUrl(videoUrl: String) {
        val context = LocalContext.current
        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.fromUri(videoUrl.toUri())
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
            }
        }
        DisposableEffect(Unit)
        {
            onDispose {
                exoPlayer.release()
            }
        }
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                }
            }
        )
    }
}
