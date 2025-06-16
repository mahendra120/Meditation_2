package com.example.meditation

import android.content.Intent
import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.ui.theme.customAppFontFamily


class signip : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mybackpage()
        }
    }

    @Composable
    @Preview(showSystemUi = true)
    fun Mybackpage() {
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Column {
                IconButton(
                    onClick = { finish() },
                    modifier = Modifier.padding(top = 100.dp, bottom = 40.dp),
                    colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Transparent)
                )
                {
                    Icon(
                        Icons.Default.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Text(
                    text = "Signup",
                    fontSize = 45.sp,
                    fontFamily = customAppFontFamily,
                    modifier = Modifier.padding(start = 25.dp)
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(bottom = 60.dp))
                Button(
                    onClick = {
                        var intent = Intent(this@signip, MainActivity::class.java)
                        startActivity(intent)
                    },
                    modifier = Modifier
                        .width(330.dp)
                        .height(60.dp),
                    border = BorderStroke(1.dp, color = Color.Black),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                )
                {
                    Row {
                        Image(
                            painter = painterResource(R.drawable.google),
                            contentDescription = null,
                            modifier = Modifier
                                .size(23.dp)
                                .padding(top = 6.dp)
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(
                            text = "Google with Signup",
                            fontSize = 20.sp,
                            fontFamily = customAppFontFamily, color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        var intent = Intent(this@signip, MainActivity::class.java)
                        startActivity(intent)
                    },
                    modifier = Modifier
                        .width(330.dp)
                        .height(60.dp),
                    border = BorderStroke(1.dp, color = Color.Black),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                )
                {
                    Row {
                        Icon(
                            Icons.Default.Call,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.padding(top = 3.dp)
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(
                            text = "Number with Signup",
                            fontSize = 20.sp,
                            fontFamily = customAppFontFamily, color = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = {
                        var intent = Intent(this@signip, MainActivity::class.java)
                        startActivity(intent)
                    },
                    modifier = Modifier
                        .width(330.dp)
                        .height(60.dp),
                    border = BorderStroke(1.dp, color = Color.Black),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                )
                {
                    Row {
                        Icon(
                            Icons.Default.Email,
                            contentDescription = null,
                            tint = Color.Black,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                        Spacer(modifier = Modifier.padding(5.dp))
                        Text(
                            text = "Google with Signup",
                            fontSize = 20.sp,
                            fontFamily = customAppFontFamily, color = Color.Black
                        )
                    }
                }
            }
        }
    }
}
