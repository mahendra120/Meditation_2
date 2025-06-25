package com.example.meditation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.meditation.ui.theme.customAppFontFamily
import kotlin.system.exitProcess


class MainActivity : ComponentActivity() {
    var page by mutableStateOf("meditation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent),
                    containerColor = Color.Transparent,
                    bottomBar = { MyBottombar() }
                ) { innerPadding ->
                    when (page) {
                        "meditation" -> MeditationPage(innerPadding) // contains MyHomepage()
                        "yoga" -> YogaPage(innerPadding)
                        "profile" -> ProfilePage(innerPadding)
                    }
                }
            }
        }
    }


    @Composable
    private fun MeditationPage(innerPadding: PaddingValues) {
        Box(
            modifier = Modifier
                .fillMaxSize()
//                .padding(innerPadding)
        )
        {
            BackgroundVideoPlayer()
            MyHomepage()
        }
    }


    @Composable
    fun YogaPage(innerPadding: PaddingValues) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        )
        {
            MyYoga()
        }
    }


    @Composable
    fun ProfilePage(innerPadding: PaddingValues) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(innerPadding)
        )
        {
            MyProfile()
        }
    }

    // naviestion bar
    @Composable
    fun MyBottombar() {
        BottomAppBar(
            containerColor = Color.White.copy(.2f)
        ) {
            IconButton(onClick = {
                page = "meditation"
            }, modifier = Modifier.weight(1f)) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = Color.White           // White icon
                )
            }
            IconButton(onClick = {
                page = "yoga"
            }, modifier = Modifier.weight(1f)) {
                Image(
                    painter = painterResource(R.drawable.lotus),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.White) // Tint image white
                )
            }
            IconButton(onClick = {
                page = "profile"
            }, modifier = Modifier.weight(1f)) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = null,
                    tint = Color.White           // White icon
                )
            }
        }
    }


    @Composable
    fun MyHomepage() {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Meditation",
                fontSize = 38.sp,
                color = Color(255, 255, 255, 255),
                fontFamily = customAppFontFamily,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 55.dp)
            )
            Button(
                onClick = {
                    var intent = Intent(this@MainActivity, Meditation::class.java)
                    startActivity(intent)
                    finish()
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 100.dp)
                    .width(340.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(2.dp, color = Color.White)
            ) {
                Text(
                    text = "Let's BOOM",
                    fontFamily = customAppFontFamily,
                    color = Color.White,
                    fontSize = 23.sp
                )
            }
        }
    }

    @Composable
    fun MyYoga() {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "Yoga",
                    fontSize = 35.sp, color = Color.White,
                    fontFamily = customAppFontFamily,
                    modifier = Modifier.padding(
                        start = screenWidth * .35f,
                        top = screenHeight * .02f
                    )
                )
                Text(
                    text = "Madras",
                    fontSize = 20.sp,
                    color = Color.White.copy(.7f), fontFamily = customAppFontFamily,
                    modifier = Modifier.padding(
                        top = screenHeight * .01f,
                        start = screenHeight * .02f,
                        bottom = 5.dp
                    )
                )
                LazyRow {
                    items(Mudra_Images.size) {
                        Card(
                            modifier = Modifier
                                .height(120.dp)
                                .width(180.dp)
                                .padding(6.dp)
                        ) {
                            Image(
                                painter = painterResource(Mudra_Images[it]),
                                contentDescription = null, contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            } // Madras
            item {
                Text(
                    "Pranayam",
                    fontSize = 20.sp, fontFamily = customAppFontFamily,
                    color = Color.White.copy(.7f),
                    modifier = Modifier.padding(
                        top = screenHeight * .01f,
                        start = screenHeight * .01f, bottom = 4.dp
                    )
                )
                LazyRow {
                    items(Pranayam_Images.size) {
                        Card(
                            modifier = Modifier
                                .height(120.dp)
                                .width(180.dp)
                                .padding(6.dp)
                        ) {
                            Image(
                                painter = painterResource(Pranayam_Images[it]),
                                contentDescription = null, contentScale = ContentScale.FillBounds,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            } // Pranayam
            item {
                Text(
                    "Asana",
                    fontSize = 20.sp, fontFamily = customAppFontFamily,
                    color = Color.White.copy(.7f),
                    modifier = Modifier.padding(start = 12.dp)
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.height((200 * (yoga_pose_Images.size / 2)).dp)
                ) {
                    items(yoga_pose_Images.size) {
                        Card(
                            modifier = Modifier
                                .padding(10.dp)
                                .width(screenWidth * .25f)
                                .height(195.dp),
                            onClick = {
                                var intent = Intent(this@MainActivity, Yogacard::class.java)
                                startActivity(intent)
                            },
                            colors = CardDefaults.cardColors(containerColor = Color.White.copy(.3f))
                        )
                        {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Image(
                                        painter = painterResource(yoga_pose_Images[it]),
                                        contentDescription = null,
                                        contentScale = ContentScale.FillBounds,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .weight(.8f)
                                    )
                                    Text(
                                        YogaPoseName[it],
                                        fontSize = 17.sp,
                                        color = Color.White, fontFamily = customAppFontFamily,
                                        modifier = Modifier
                                            .weight(.2f)
                                            .padding(top = 9.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                            }
                        }

                    }
                }
            } // Asana
        }
    }


    @Composable
    @Preview(showSystemUi = true)
    fun MyProfile() {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        LazyColumn {
            item {
                Text(
                    text = "Profile",
                    fontSize = 35.sp, color = Color.White,
                    fontFamily = customAppFontFamily,
                    modifier = Modifier.padding(
                        start = screenWidth * .35f,
                        top = screenHeight * .02f
                    )
                )
                Row(modifier = Modifier.padding(top = 14.dp)) {
                    Card(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(.3f)
                            .padding(
                                top = screenHeight * .033f,
                                start = screenHeight * .015f,
                                end = screenHeight * .02f
                            ),
                        shape = (RoundedCornerShape(15.dp))
                    )
                    {
                        Image(
                            painter = painterResource(R.drawable.profile),
                            contentDescription = null
                        )

                    }
                    Column(
                        modifier = Modifier
                            .weight(.5f)
                            .padding(start = 2.dp)
                    ) {
                        Spacer(modifier = Modifier.padding(top = screenHeight * .04f))

                        Text(text = "Gohil Mahendra", fontSize = 22.sp, color = Color.White)

                        Spacer(modifier = Modifier.padding(top = screenHeight * .01f))

                        Text(
                            text = "gohilmahendra424@gmail.com",
                            fontSize = 17.sp,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.padding(top = screenHeight * .01f))

                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                            border = BorderStroke(1.dp, Color.White)
                        )
                        {
                            Text(text = "Update Profile", color = Color.White)
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(bottom = screenHeight * .025f))
            }//firstColumn
            item {
                Text(
                    text = "My Stats ",
                    fontSize = 20.sp, fontFamily = customAppFontFamily,
                    color = Color.White,
                    modifier = Modifier.padding(start = 15.dp)
                )
                Spacer(modifier = Modifier.padding(bottom = screenHeight * .015f))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = screenHeight * .01f, end = screenHeight * .01f)
                        .height(screenHeight * .35f), colors = CardDefaults.cardColors(containerColor = Color.White.copy(.15f))
                ) {

                }
            }//secendColumn
        }
    }

    override fun onUserLeaveHint() {
        super.onUserLeaveHint()
        finishAffinity()  // all activities close
        exitProcess(0)    // app completely exit
    }
}

@OptIn(UnstableApi::class)
@Composable
fun BackgroundVideoPlayer() {
    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val videoUri =
                Uri.parse("android.resource://${context.packageName}/${R.raw.home_page2}")
            val mediaItem = MediaItem.fromUri(videoUri)
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
            repeatMode = ExoPlayer.REPEAT_MODE_ONE
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = {
            PlayerView(it).apply {
                player = exoPlayer
                useController = false
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}
