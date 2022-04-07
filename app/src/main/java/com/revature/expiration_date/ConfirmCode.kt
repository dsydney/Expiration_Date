package com.revature.expiration_date

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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

class ConfirmCode : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Expiration_DateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ConfirmEmailScreen()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConfirmEmailScreen() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val navController = rememberNavController()
    val clicked = rememberSaveable{ mutableStateOf(false)}

    Column {
        TopAppBar(title = {
            Text(text = "Confirm Email Screen")
        })
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            val confirmationCode = rememberSaveable {
                mutableStateOf("")
            }
            var codeIsCorrect = false
            TextField(
                value = confirmationCode.value,
                onValueChange = { confirmationCode.value = it },
                label = {
                    Text(text = "Enter Code that was emailed to you")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )
            Button(onClick = {
                if (confirmationCode.value.equals("12345")) {//Obviously, this code should be one that was emailed to the user
                    codeIsCorrect = true//Upon confirming their email, the user should be added to a DB, with their email, username, password, and eventually their data
                    //context.startActivity(Intent(context, ProductEntry()::class.java))
                }
                if (codeIsCorrect == true) {
                    //Send to product Entry
                    clicked.value = true
                    Toast.makeText(context, "Correct", Toast.LENGTH_LONG).show()
                    //context.startActivity(Intent(context, ProductEntry()::class.java))
                } else {
                    Toast.makeText(context, "Incorrect", Toast.LENGTH_LONG).show()
                }
            }) {
                Text(text = "Submit")
            }
        }
    }
    if (clicked.value) {
        BottomNavBar(navController = navController, startScreen = "add")
    }
}