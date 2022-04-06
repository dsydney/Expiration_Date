package com.revature.expiration_date

import android.app.DatePickerDialog
import android.content.Context
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.core.content.ContextCompat.startActivity
import com.revature.expiration_date.ui.theme.Expiration_DateTheme
import com.revature.expiration_date.viewmodel.ProductsViewModel
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
                TextField(value = selectedItem,
                    onValueChange = {selectedItem = it},
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textFilledSize = coordinates.size.toSize()
                        },
                    label = {Text(text = defaultText)},
                    trailingIcon = {
                        Icon(icon, "", Modifier
                            .clickable {expanded = !expanded}
                            .background(color = MaterialTheme.colors.primary))
                    }
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
fun datepicker(): String {
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
            date.value = "${month+1}/$dayOfMonth/$year"
        }, year, month, day
    )

    Button(onClick = {
        datePickerDialog.show()
    }) {
        Text(text = "Enter Expiration Date")
    }
    Spacer(modifier = Modifier.size(16.dp))

    Text(text = "Selected Date: ${date.value}", fontSize = 15.sp, textAlign = TextAlign.Center)

    return date.value

}

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductEntryScreen( /* viewModel: ProductsViewModel */ ) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var expanded by remember {mutableStateOf (false)}
    val productList = listOf("Eggs", "Milk", "Lettuce", "Pork chops")
    val categoryList = listOf("Dairy", "Vegetables", "Meat", "Fruits")
    val locationList = listOf("Fridge", "Freezer", "Pantry", "Counter")
    val location = rememberSaveable{mutableStateOf("")}
    var productName by rememberSaveable {mutableStateOf("")}
    val category = rememberSaveable {mutableStateOf("")}
    var textFiledSize by remember { mutableStateOf(Size.Zero)}

    var icon = if (expanded) {
        Icons.Filled.KeyboardArrowUp
    }else{
        Icons.Filled.KeyboardArrowDown
    }
    Column {
        TopAppBar(title = { Text("Product Entry") })


        Column (modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center)
        {

            //Contents of Column:
            // product dropdown menu
            // expiration date date picker
            // category dropdown menu
            // location dropdown menu
            // photo - front
            // photo - back

            dropDownMenu(list = productList, "Select Item")

            Spacer(modifier = Modifier.size(16.dp))

            datepicker()

            Spacer(modifier = Modifier.size(16.dp))

            dropDownMenu(list = categoryList, "Select Category")

            Spacer(modifier = Modifier.size(16.dp))

            dropDownMenu(list = locationList, "Select Location")

            Spacer(modifier = Modifier.size(40.dp))

            photos()


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

