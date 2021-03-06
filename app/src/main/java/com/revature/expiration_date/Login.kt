package com.revature.expiration_date

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.revature.expiration_date.productview.ProductView
import com.revature.expiration_date.ui.theme.Expiration_DateTheme
import com.revature.expiration_date.viewmodels.UserViewModel

class Login : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        setContent {
            Expiration_DateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LoginScreen(userViewModel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(userViewModel: UserViewModel) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val navController = rememberNavController()
    val clicked = rememberSaveable{ mutableStateOf(false)}
    val userList = userViewModel.readAllData().observeAsState(arrayListOf())

    Column {
        TopAppBar(title = {
            Text(text = "Login Screen")
        })
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //Icon
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier.size(50.dp)
                )
            }



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
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )



            //TextField Password
            val word = rememberSaveable {
                mutableStateOf("")
            }
            TextField(
                value = word.value,
                onValueChange = {word.value = it},
                label = {
                    Text(text = "Password")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                visualTransformation = PasswordVisualTransformation(), // Makes the password appear as asterisks
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )
            //Button 'Login' -> Product View


            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
            ) {
                Button(onClick = {
                    /* viewModel.login(email, password) */
                    //Use an if statement
                    //Toast message if they don't match, send to next screen if they do match
                    //               if (password.value == "qwerty" && username.value == "tombom") { //Eventually, this should search an DB for registered users
                    //                   context.startActivity(Intent(context, ProductView()::class.java))
                    //                   clicked.value = true
                    //               }
                    //This is using the DB for registered users
                    val holder = userList.value
                    holder.forEach { user ->
                        if (username.value == user.name && word.value == user.password) {
                            clicked.value = true
                        } else if(username.value == "admin" && word.value == "admin") {
                            clicked.value = true
                        } else {
                            Toast.makeText(context, "Incorrect Password or Username", Toast.LENGTH_SHORT)
                            .show()
                        }
                    }
                }, enabled = word.value.isNotBlank() && username.value.isNotBlank()
                ) {
                    Text(text = "Login")
                }
            }
            
            
            ClickableText(
                text = AnnotatedString(
                    "Don't have an account yet? Click here to sign up!"
                ),
                //fontSize = 10.sp,
                onClick = {
                    context.startActivity(Intent(context, Registration()::class.java))
                }
            )
            
            //Spacer(modifier = Modifier.height(200.dp))
        }
    }
    if (clicked.value) {
        BottomNavBar(navController = navController, startScreen = "view")
    }
}

