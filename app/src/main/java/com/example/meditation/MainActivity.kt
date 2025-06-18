package com.example.meditation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.ui.theme.customAppFontFamily
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    var page by mutableStateOf("meditation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
                MyBottombar()
            }) { innerPadding ->
                when (page) {
                    "meditation" -> MeditationPage(innerPadding)
                    "yoga" -> YogaPage(innerPadding)
                    "profile" -> ProfilePage(innerPadding)
                }
            }
        }
    }


    @Composable
    private fun MeditationPage(innerPadding: PaddingValues) {
        Image(
            painter = painterResource(R.drawable.meditation_home),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
        {
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
        )
        {
            MyProfile()
        }
    }


    @Composable
    fun MyBottombar() {
        BottomAppBar(
            containerColor = Color.Transparent.copy(.5f)
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
    @Preview
    fun MyHomepage() {
        Box(modifier = Modifier.fillMaxSize()) {

            Text(
                text = "Meditation",
                fontSize = 30.sp,
                color = Color(201, 201, 201, 255),
                fontFamily = customAppFontFamily,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 55.dp)
            )
            Button(
                onClick = {
                    var intent = Intent(this@MainActivity, Meditation::class.java)
                    startActivity(intent)
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 50.dp)
                    .width(340.dp)
                    .height(55.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(1.dp, color = Color.White)
            ) {
                Text(
                    text = "Let's BOOM",
                    fontFamily = customAppFontFamily,
                    color = Color.White,
                    fontSize = 22.sp
                )
            }
        }
    }

    @Composable
    fun MyYoga() {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            item {
                Text(
                    text = "Madras",
                    fontSize = 20.sp,
                    color = Color.White.copy(.7f),
                    modifier = Modifier.padding(top = 60.dp, start = 15.dp, bottom = 5.dp)
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
                    fontSize = 20.sp,
                    color = Color.White.copy(.7f),
                    modifier = Modifier.padding(top = 7.dp, start = 12.dp, bottom = 4.dp)
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
            items(yoga_pose.size) {
                Text(
                    "Asana",
                    fontSize = 20.sp,
                    color = Color.White.copy(.7f),
                    modifier = Modifier.padding(top = 9.dp, start = 12.dp, bottom = 4.dp)
                )
                Column() {
                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .width(50.dp)
                            .height(210.dp)
                    )
                    {
                        Image(
                            painter = painterResource(yoga_pose[it]),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        } // Asana
    }


    @Composable
    fun MyProfile() {

    }
}
