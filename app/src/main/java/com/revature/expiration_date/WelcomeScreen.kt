package com.revature.expiration_date

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.revature.expiration_date.ui.theme.Expiration_DateTheme

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
                    Main()
                }
            }
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
            Modifier.fillMaxWidth().fillMaxHeight(0.5f),
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