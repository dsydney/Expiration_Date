package com.revature.expiration_date

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
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

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductEntryScreen() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current

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
            //TextField Product entry
            val productName = rememberSaveable{
                mutableStateOf("")
            }
            TextField(
                value = productName.value,
                onValueChange = {productName.value = it},
                label = {
                    Text(text = "Enter Product Name")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )
            //I will be trying to use a search function with the Product entry field
            //TextField Expiration Date entry (Can we get a calendar to pop up
            // so we can click on a date?) That is my plan.
            val expirationDate = rememberSaveable{
                mutableStateOf("")
            }
            TextField(
                value = expirationDate.value,
                onValueChange = {expirationDate.value = it},
                label = {
                    Text(text = "Enter Expiration Date")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )

            //Dropdown list Category
            val category = rememberSaveable{
                mutableStateOf("")
            }
            TextField(
                value = category.value,
                onValueChange = {category.value = it},
                label = {
                    Text(text = "Enter Category")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )

            //Dropdown list Location
            val location = rememberSaveable{
                mutableStateOf("")
            }
            TextField(
                value = location.value,
                onValueChange = {location.value = it},
                label = {
                    Text(text = "Enter Location")
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )

            //Take a picture of the front
            Button(onClick = {

                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                context.startActivity(intent)

            }) {

                Text(text = "Take Picture of Item")

            }
            //Take a picture of the expiration date
            Button(onClick = {

                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                context.startActivity(intent)

            }) {

                Text(text = "Take Picture of Expiration Date")

            }

            Button(onClick = {
                //Save new item to ... a database? An arrayList? An arraylist of Items, where Items
                // is a class with parameters for name, location, category, etc could work
            }) {
                Text(text = "Save")
            }

            Spacer(modifier = Modifier.height(10.dp))

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

