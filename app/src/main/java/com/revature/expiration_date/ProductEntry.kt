package com.revature.expiration_date

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.revature.expiration_date.ui.theme.Expiration_DateTheme

class ProductEntry : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Expiration_DateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProductEntryScreen()
                }
            }
        }
    }
}

@Composable
fun ProductEntryScreen() {
    Column {
        //Top App Bar
        TopAppBar() {
            Text(text = "Product Entry")
        }
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //TextField Prodect entry
            val productName = rememberSaveable{
                mutableStateOf("")
            }
            TextField(
                value = productName.value,
                onValueChange = {productName.value = it},
                label = {
                    Text(text = "Enter Product Name")
                }
            )
            //TextField Expiration Date entry (Can we get a calendar to pop up
            // so we can click on a date?)
            //Dropdown list Category
            //Dropdown list Location
            //Take a picture of the front
            //Take a picture of the expiration date
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    Expiration_DateTheme {
        ProductEntryScreen()
    }
}

