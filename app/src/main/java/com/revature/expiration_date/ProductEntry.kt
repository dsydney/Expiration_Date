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
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
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

@Composable
fun dropDownMenu(list: List<String>, defaultText: String): String {

    var expanded by remember { mutableStateOf(false) }
    //val list = listOf("a", "b", "c", "d")
    var selectedItem by remember { mutableStateOf("") }
    var textFilledSize by remember { mutableStateOf(Size.Zero) }
    val icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column (
        modifier = Modifier
            .padding(20.dp)
    ) {
        Box(
            //value = selectedItem,
            //onValueChange = { selectedItem = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFilledSize = coordinates.size.toSize()
                }
                .border(border = BorderStroke(2.dp, Color.Black))
                .padding(3.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = defaultText)

                Spacer(modifier = Modifier.width(10.dp))

                Text(text = selectedItem)

                Spacer(modifier = Modifier.width(10.dp))

                Icon(
                    icon, "",
                    Modifier
                        .clickable { expanded = !expanded }
                        .background(color = MaterialTheme.colors.primary)
                    )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current){textFilledSize.width.toDp()})
        ) {
            list.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedItem = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
    }

    return selectedItem
}

@Composable
fun photos() {
    //Take a picture of the front
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Button(onClick = {}) {
            Image(
                painterResource(id = R.drawable.camera_icon),
                contentDescription = "Camera icon",
                modifier = Modifier.size(20.dp)
            )

            Text(text = "Add a photo", Modifier.padding(start = 10.dp))
        }

        Button(onClick = {}) {
            Image(
                painterResource(id = R.drawable.image_icon),
                contentDescription = "Image icon",
                modifier = Modifier.size(20.dp)
            )

            Text(text = "Add an image", Modifier.padding(start = 10.dp))
        }


    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun datepicker() {
    //Expiration Date
    val context = LocalContext.current
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$month/$year"
        }, year, month, day
    )

//            OutlinedTextField(Text(text = "Selected Date: ${date.value}")
    Spacer(modifier = Modifier.size(16.dp))
    Button(onClick = {
        datePickerDialog.show()
    }) {
        Text(text = "Enter Expiration Date")
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
    val locationList = listOf("Fridge", "Freezer", "Pantry", "Counter")
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
        TopAppBar(title = { Text("Product Entry") })


        Column {

            //Contents of Column:
            // product dropdown menu
            // expiration date date picker
            // category dropdown menu
            // location dropdown menu
            // photo - front
            // photo - back

            dropDownMenu(list = productList, "Select Item")
            datepicker()
            dropDownMenu(list = categoryList, "Select Category")
            dropDownMenu(list = locationList, "Select Location")
            photos()

/*
            Card(
                Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(10.dp),
                elevation = 12.dp,
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(3.dp, Color.Black),
                backgroundColor = Color.LightGray
            )
            {
                OutlinedTextField(
                    value = productName,
                    onValueChange = { productName = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textFiledSize = coordinates.size.toSize()
                        },
                    label = { Text(text = "Enter Product Name") },
                    trailingIcon = {
                        Icon(icon, "", Modifier.clickable { expanded = !expanded })
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() })
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
            }
            //Expiration Date
            val year: Int
            val month: Int
            val day: Int

            val calendar = Calendar.getInstance()
            year = calendar.get(Calendar.YEAR)
            month = calendar.get(Calendar.MONTH)
            day = calendar.get(Calendar.DAY_OF_MONTH)
            calendar.time = Date()

            val date = remember { mutableStateOf("") }
            val datePickerDialog = DatePickerDialog(
                context,
                { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                    date.value = "$dayOfMonth/$month/$year"
                }, year, month, day
            )

//            OutlinedTextField(Text(text = "Selected Date: ${date.value}")
            Spacer(modifier = Modifier.size(16.dp))
            Button(onClick = {
                datePickerDialog.show()
            }) {
                Text(text = "Enter Expiration Date")
            }
//                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
//                keyboardActions = KeyboardActions(
//                    onDone = {keyboardController?.hide()})


            //Dropdown list Category

            OutlinedTextField(
                value = category.value,
                onValueChange = { category.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        textFiledSize = coordinates.size.toSize()
                    },
                label = { Text(text = "Enter Category") },
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable { expanded = !expanded })
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() })
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
                label = { Text(text = "Enter Location") },
                trailingIcon = {
                    Icon(icon, "", Modifier.clickable { expanded = !expanded })
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() })
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                Button(onClick = {}) {
                    Image(
                        painterResource(id = R.drawable.camera_icon),
                        contentDescription = "Camera icon",
                        modifier = Modifier.size(20.dp)
                    )

                    Text(text = "Add a photo", Modifier.padding(start = 10.dp))
                }

                Button(onClick = {}) {
                    Image(
                        painterResource(id = R.drawable.image_icon),
                        contentDescription = "Image icon",
                        modifier = Modifier.size(20.dp)
                    )

                    Text(text = "Add an image", Modifier.padding(start = 10.dp))
                }


            }


 */
        }


//        BottomBar()
    }
}

@Composable
fun BottomBar() {

    val selectedIndex = remember { mutableStateOf(0) }
    BottomNavigation(elevation = 10.dp) {

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Delete, "")
        },
            label = { Text(text = "Cancel") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })

        BottomNavigationItem(icon = {
            Icon(imageVector = Icons.Default.Check, "")
        },
            label = { Text(text = "OK") },
            selected = (selectedIndex.value == 0),
            onClick = {
                selectedIndex.value = 0
            })


    }


}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    Expiration_DateTheme {

    }
}

