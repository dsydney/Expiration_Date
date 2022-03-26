package com.revature.expiration_date

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.revature.expiration_date.ui.theme.Expiration_DateTheme

class Login : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Expiration_DateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val navController = rememberNavController()
    val clicked = rememberSaveable{ mutableStateOf(false)}

    Column {
        TopAppBar() {
            Text(text = "Login Screen")
        }
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //TextField Username
            val username = rememberSaveable {
                mutableStateOf("")
            }
            TextField(
                value = username.value,
                onValueChange = {username.value = it},
                label = {
                    Text(text = "Username")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )
            //TextField Password
            val password = rememberSaveable {
                mutableStateOf("")
            }
            TextField(
                value = password.value,
                onValueChange = {password.value = it},
                label = {
                    Text(text = "Password")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )
            //Button 'Login' -> Product View


            Button(onClick = {
                //Use an if statement
                //Toast message if they don't match, send to next screen if they do match
                if (password.value == "qwerty" && username.value == "tombom") { //Eventually, this should search an DB for registered users
                    //context.startActivity(Intent(context, ProductView()::class.java))
                    clicked.value = true
                } else {
                    Toast.makeText(context, "Incorrect Password or Username", Toast.LENGTH_LONG).show()
                }
            }) {
                Text(text = "Login")
            }
        }
    }
    if (clicked.value) {
        BottomNavBar(navController = navController, startScreen = "view")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    Expiration_DateTheme {
        LoginScreen()
    }
}