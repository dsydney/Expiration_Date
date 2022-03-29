package com.revature.expiration_date

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.content.ContextCompat.startActivity
import com.revature.expiration_date.ui.theme.Expiration_DateTheme
import java.util.*

class ProductEntry : ComponentActivity() {
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
                    ProductEntryScreen()
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductEntryScreen() {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var expanded by remember {mutableStateOf (false)}
    val productList = listOf("Eggs", "Milk", "Lettuce", "Pork chops")
    val categoryList = listOf("Dairy", "Vegetables", "Meat", "Fruits")
    val locationList = listOf("fridge", "freezer", "pantry", "counter")
    val location = rememberSaveable{mutableStateOf("")}
    var productName by rememberSaveable {mutableStateOf("")}
    var category = rememberSaveable {mutableStateOf("")}
    var textFiledSize by remember { mutableStateOf(Size.Zero)}

    var icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }

    Column {
        //Top App Bar
        TopAppBar() {
            Text(text = "Product Entry")
        }
            //TextField Product entry
//            val productName = rememberSaveable{
//                mutableStateOf("")

            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                    label = {Text(text = "Enter Product Name")},
                        trailingIcon = {
                    Icon(icon, "", Modifier.clickable { expanded = !expanded })
                },
                  keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                  keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier
                    .width(with(LocalDensity.current) { textFiledSize.width.toDp() })
            ) {
                productList.forEach { label ->
                    DropdownMenuItem(onClick = {
                        productName = label
                        expanded = false
                    }) {
                        Text(text = label)
                    }
                }
            }
            //Expiration Date
 /*
            val year: Int
            val month: Int
            val day: Int

            val calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)
            calendar.time = Date()

        val date = remember{mutableStateOf("")}
        val datePickerDialog = DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                date.value = "$dayOfMonth/$month/$year"
            }, year, month, day)

            OutlinedTextField(Text(text = "Selected Date: ${date.value}")
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    datePickerDialog.show()
                } )  {
                Text(text = "Enter Expiration Date")
            },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )
*/
            //Dropdown list Category

         OutlinedTextField(
            value = category.value,
            onValueChange = { category.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFiledSize = coordinates.size.toSize()
                },
            label = {Text(text = "Enter Category")},
            trailingIcon = {
                Icon(icon, "", Modifier.clickable { expanded = !expanded })
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {keyboardController?.hide()})
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFiledSize.width.toDp() })
        ) {
            categoryList.forEach { label ->
                DropdownMenuItem(onClick = {
                    category.value = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }

            //Dropdown list Location

            OutlinedTextField(
                value = location.value,
                onValueChange = { location.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = {Text(text = "Enter Location")},
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable { expanded = !expanded })
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = {keyboardController?.hide()})
            )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFiledSize.width.toDp() })
        ) {
            locationList.forEach { label ->
                DropdownMenuItem(onClick = {
                    location.value = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }



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



@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    Expiration_DateTheme {
        ProductEntryScreen()
    }
}

