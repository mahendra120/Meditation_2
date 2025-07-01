package com.example.meditation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.meditation.ui.theme.customAppFontFamily

class UpdateProfile : ComponentActivity() {
    val sp by lazy {
        getSharedPreferences("lecture", MODE_PRIVATE)
    }
    var name by mutableStateOf("")
    var surname by mutableStateOf("")
    var email by mutableStateOf("")

    var nameError by mutableStateOf("")
    var surnameError by mutableStateOf("")
    var emailError by mutableStateOf("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        name = sp.getString("name", "") ?: ""
        surname = sp.getString("surname", "") ?: ""
        email = sp.getString("email", "") ?: ""

        enableEdgeToEdge()
        setContent {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Button(
                    onClick = {
                        finish()
                        var intent = Intent(this@UpdateProfile, MainActivity::class.java)
                        startActivity(intent)
                    },
                    modifier = Modifier.padding(top = 45.dp, start = 9.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(.05.dp, color = Color.White)
                ) {
                    Text(
                        text = "<",
                        fontSize = 15.sp,
                        fontFamily = customAppFontFamily,
                        color = Color.White
                    )
                }


                Myfunction()
            }
        }
    }

    @Composable
    fun Myfunction() {
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp
        val screenWidth = configuration.screenWidthDp.dp
        var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let {
                contentResolver.takePersistableUriPermission(
                    it,
                    Intent.FLAG_GRANT_READ_URI_PERMISSION
                )
                selectedImageUri = it
            }
        }
        Card(
            onClick = {
                launcher.launch("image/*")
            },
            modifier = Modifier
                .padding(top = screenHeight * .2f, start = screenHeight * .135f)
                .height(160.dp)
                .width(150.dp),
            shape = (RoundedCornerShape(15.dp)),

            ) {
            if (selectedImageUri != null) {
                Image(
                    painter = rememberAsyncImagePainter(selectedImageUri),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.profile),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 280.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = name, onValueChange = { name = it }, label = {
                    Text(
                        "Enter The Name ", fontFamily = customAppFontFamily, color = Color.Gray
                    )
                },
                isError = nameError.isNotEmpty(),
                supportingText = {
                    Text(
                        text = nameError,
                        color = Color.Red,
                        fontFamily = customAppFontFamily,
                        fontSize = 12.sp
                    )
                })
            Spacer(
                modifier = Modifier.padding(
                    top = screenHeight * .03f, end = screenHeight * .03f
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
                },
                isError = surnameError.isNotEmpty(),
                supportingText = {
                    Text(
                        text = surnameError,
                        color = Color.Red,
                        fontFamily = customAppFontFamily,
                        fontSize = 12.sp
                    )
                }
            )
            Spacer(
                modifier = Modifier.padding(
                    top = screenHeight * .03f, end = screenHeight * .03f
                )
            )
            TextField(
                value = email, onValueChange = { email = it }, label = {
                    Text(
                        "Enter the Email ", fontFamily = customAppFontFamily, color = Color.Gray
                    )
                },
                isError = emailError.isNotEmpty(),
                supportingText = {
                    Text(
                        text = emailError,
                        color = Color.Red,
                        fontFamily = customAppFontFamily,
                        fontSize = 12.sp
                    )
                })
            Spacer(modifier = Modifier.padding(top = 60.dp))
            Button(
                onClick = {
                    sp?.edit()?.apply {
                        if (name.isEmpty()) {
                            nameError = "enter name"
                            return@Button
                        } else {
                            nameError = ""
                        }
                        if (surname.isEmpty()) {
                            surnameError = "enter suname"
                            return@Button
                        } else {
                            surnameError = ""
                        }
                        if (email.isEmpty()) {
                            emailError = "enter email"
                            return@Button
                        } else {
                            emailError = ""
                        }
                        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            emailError = "enter valid email"
                            return@Button
                        } else {
                            emailError = ""
                        }

                        putString("name", name)
                        putString("surname", surname)
                        putString("email", email)
                        selectedImageUri?.let {
                            putString(
                                "profile_image_uri",
                                selectedImageUri.toString()
                            ) // Save URI as string
                        }
                        apply()
                        finish()
                        var intent = Intent(this@UpdateProfile, MainActivity::class.java)
                        startActivity(intent)
                    }
                },
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(1.dp, Color.White)
            ) {
                Text("Add", fontFamily = customAppFontFamily, fontSize = 20.sp)
            }
        }
    }
}