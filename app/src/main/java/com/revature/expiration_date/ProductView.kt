package com.revature.expiration_date

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val context = LocalContext.current

    Column {
        //TopAppBar
        TopAppBar() {
            Text(text = "Product View")
        }
        Column (
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            //Scrollable box (vertical Scroll) with a list
            Card(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .padding(10.dp),
                elevation = 12.dp,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(3.dp, Color.Black),
                backgroundColor = Color.LightGray
            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .verticalScroll(
                            rememberScrollState()
                        )
                ) {
                    // List Item = Row
                    Column(Modifier.padding(10.dp)) {
                        //Picture of item, Name of Item, Days remaining
                        Row() {
                            Text(text = "Item 1\n\n\n\n\n\n\n")
                        }
                        Row() {
                            Text(text = "Item 2\n\n\n\n\n\n\n")
                        }
                        Row() {
                            Text(text = "Item 3\n\n\n\n\n\n\n")
                        }
                        Row() {
                            Text(text = "Item 4\n\n\n\n\n\n\n")
                        }
                        Row() {
                            Text(text = "Item 5\n\n\n\n\n\n\n")
                        }
                        Row() {
                            Text(text = "Item 6\n\n\n\n\n\n\n")
                        }
                    } // End column in box
                } // End box - Product view box
            } // End card - product view card

            Spacer(Modifier.height(60.dp))

            //Button - Delete item
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.End
                    ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Delete Item")
                }
            }

            Spacer(Modifier.height(60.dp))

            //Button - Add item -> ProductEntry
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = {
                    Toast.makeText(context, "Go to Add Item", Toast.LENGTH_LONG).show()
                    context.startActivity(Intent(context, ProductEntry()::class.java))
                }) {
                    Text(text = "Add Item")
                }
            }
            //Nav Drawer
            //Different Locations, changes the Scrollable Box
            //Refrigerator, Freezer, Pantry, All, Expiring Tomorrow, and any other customizable locations
        } // End column - screen
    } // End column - TopAppBar and screen
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    Expiration_DateTheme {
        ProductViewScreen()
    }
}