package com.example.meditation

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import androidx.core.net.toUri

class Yogacard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // ✅ Test Video URL
            MainScreen()
        }
    }

    @OptIn(UnstableApi::class)
    @Composable
    fun MainScreen() {
        val context = LocalContext.current
        val videoUrl = "https://files.catbox.moe/o1w88b.mp4"

        val exoPlayer = ExoPlayer.Builder(context).build()
        val mediaItem = MediaItem.fromUri(videoUrl.toUri())
        exoPlayer.setMediaItem(mediaItem)

        val playerVeiwc = StyledPlayerView(context)
        playerVeiwc.player = exoPlayer

        DisposableEffect(AndroidView(factory = { playerVeiwc })) {
            exoPlayer.prepare()
            exoPlayer.playWhenReady = true
            onDispose {
                exoPlayer.release()
            }
        }
    }
}
