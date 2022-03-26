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
import com.revature.expiration_date.ui.theme.Expiration_DateTheme

class Registration : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Expiration_DateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    RegistrationScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RegistrationScreen() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        TopAppBar() {
            Text(text = "Registration Screen")
        }
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //TextField Email
            val email = rememberSaveable {
                mutableStateOf("")
            }
            TextField(
                value = email.value,
                onValueChange = {email.value = it},
                label = {
                    Text(text = "Email")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )
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
            var password = rememberSaveable {
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
            //TextField Confirm Password
            var confirmpassword = rememberSaveable {
                mutableStateOf("")
            }
            TextField(
                value = confirmpassword.value,
                onValueChange = {confirmpassword.value = it},
                label = {
                    Text(text = "Password")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
                
            )


            //Button 'Register' -> TextField emailed code, Button 'send email again' -> Product Entry
            Button(onClick = {
                //Use an if statement
                //Toast message if they don't match, send to next screen if they do match
                if (password.value == confirmpassword.value) {
                    Toast.makeText(context, "Passwords match", Toast.LENGTH_LONG).show()
                    context.startActivity(Intent(context, ConfirmCode()::class.java))
                } else {
                    Toast.makeText(context, "Passwords do not match", Toast.LENGTH_LONG).show()
                }
            }) {
                Text(text = "Register")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    Expiration_DateTheme {
        RegistrationScreen()
    }
}