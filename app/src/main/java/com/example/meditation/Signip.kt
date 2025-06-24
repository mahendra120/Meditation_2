package com.example.meditation

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
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
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        Box(
            modifier = Modifier
                .fillMaxSize()
        )
        {
            Text(
                text = "Signup",
                fontSize = 45.sp,
                fontFamily = customAppFontFamily,
                modifier = Modifier.padding(start = screenHeight * .03f, top = screenHeight * .25f)
            )
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = screenHeight * .42f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
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
                                .size(24.dp)
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
