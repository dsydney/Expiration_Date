package com.revature.expiration_date

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revature.expiration_date.ui.theme.Expiration_DateTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Expiration_DateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_screen"
    ) {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }
        composable("main_screen") {
            Main()
        }

    }

}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(key1 = true) {
        delay(5000L)
        navController.navigate("main_screen")
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Text(modifier = Modifier.padding(15.dp), text = "Expiration Date", color = MaterialTheme.colors.onPrimary, fontSize = 30.sp)
        }
    }
}

@Composable
fun Main() {
    /*
    This Welcome screen should have title at the top (name of the app),
    and then a register and login button, spaced in the center of the screen XXRegisterX'or'XLoginXX,
    where each X represents a space of standard size
     */

    val context = LocalContext.current
    Column {

        //Title
        TopAppBar() {
            Text(text = "Expiration Date")
        }
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //Registration Button
            Button(onClick = {
                Toast.makeText(context, "Go to Registration Screen", Toast.LENGTH_LONG).show()
                context.startActivity(Intent(context, Registration()::class.java))
            }) {
                Text("Register")
            }

            // text 'or'
            Text(text = "or")

            //Login Button
            Button(onClick = {
                Toast.makeText(context, "Go to Login Screen", Toast.LENGTH_LONG).show()
                context.startActivity(Intent(context, Login()::class.java))
            }) {
                Text(text = "Login")
            }

        } // End Column
    } // End Outer Column
} // End Main()

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Expiration_DateTheme {
        Main()
    }
}