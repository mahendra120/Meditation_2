package com.example.meditation

import android.content.Intent
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.meditation.ui.theme.customAppFontFamily

class UpdateProfile : ComponentActivity() {
    val sp by lazy {
        getSharedPreferences("lecture", MODE_PRIVATE)
    }
    var name by mutableStateOf("")
    var surname by mutableStateOf("")
    var email by mutableStateOf("")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Myfunction()
            }
        }
    }

    @Composable
    fun Myfunction() {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        Card(
            modifier = Modifier
                .padding(top = screenHeight * .12f, start = screenHeight * .135f)
                .height(160.dp)
                .width(150.dp), shape = (RoundedCornerShape(15.dp))
        ) {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 190.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = name,
                onValueChange = { name = it },
                label = {
                    Text(
                        "Enter The Name ",
                        fontFamily = customAppFontFamily,
                        color = Color.Gray
                    )
                }
            )
            Spacer(
                modifier = Modifier.padding(
                    top = screenHeight * .03f,
                    end = screenHeight * .03f
                )
            )
            TextField(
                value = surname,
                onValueChange = { surname = it },
                label = {
                    Text(
                        "Enter the surname ",
                        fontFamily = customAppFontFamily,
                        color = Color.Gray
                    )
                }
            )
            Spacer(
                modifier = Modifier.padding(
                    top = screenHeight * .03f,
                    end = screenHeight * .03f
                )
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        "Enter the Email ",
                        fontFamily = customAppFontFamily,
                        color = Color.Gray
                    )
                }
            )
            Spacer(modifier = Modifier.padding(top = 60.dp))
            Button(
                onClick = {
                    sp?.edit()?.apply {
                        this.putString("surname", surname) ?: ""
                        this.apply()
                    }
                    sp?.edit()?.apply {
                        this.putString("name", name) ?: ""
                        this.apply()
                    }
                    sp?.edit()?.apply {
                        this.putString("email", email) ?: ""
                        this.apply()
                    }
                    val intent = Intent(this@UpdateProfile, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }, modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text("Add", fontFamily = customAppFontFamily, fontSize = 20.sp)
            }
        }
    }
}