package com.revature.expiration_date

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.revature.expiration_date.ui.theme.Expiration_DateTheme

class ProductView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Expiration_DateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ProductViewScreen()                }
            }
        }
    }
}

@Composable
fun ProductViewScreen()  {
    Column {
        //TopAppBar
        TopAppBar() {
            Text(text = "Product View")
        }
        Column (
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //Scrollable box (vertical Scroll) with a list
            // List Item = Row
            //Picture of item, Name of Item, Days remaining
            //Button - Delete item
            //Button - Add item -> ProductEntry
            //Nav Drawer
            //Different Locations, changes the Scrollable Box
            //Refrigeratoe, Freezer, Pantry, All, Expiring Tomorrow, and any other customizable locations
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    Expiration_DateTheme {
        ProductViewScreen()
    }
}