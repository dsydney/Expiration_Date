package com.revature.expiration_date.productview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.revature.expiration_date.R
import com.revature.expiration_date.ui.theme.Expiration_DateTheme
import com.revature.expiration_date.viewmodel.ProductsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

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
                    //Add dependency

                    ProductViewScreen()
                }
            }
        }
    }
}

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Refrigerator: NavigationItem("refrigerator", R.drawable.ic_refrigerator, "Refrigerator")
    object Freezer: NavigationItem("freezer", R.drawable.ic_freezer, "Freezer")
    object Counter: NavigationItem("counter", R.drawable.ic_counter, "Counter")
    object Pantry: NavigationItem("pantry", R.drawable.ic_pantry, "Pantry")
}

@Composable
fun ProductViewScreen( /* viewModel: ProductsViewModel */ ) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {TopBar(scope = scope, scaffoldState = scaffoldState) },
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        }
    ) {
        Navigation(navController = navController)
    }
}

@Composable
fun TopBar(scope: CoroutineScope, scaffoldState: ScaffoldState) {
    TopAppBar(
        title = { Text(text = "Product View") },

        navigationIcon = {
            IconButton(onClick = {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }) {
                Icon(Icons.Filled.Menu, "")
            }
        },

        //backgroundColor = Color.Green,
        //contentColor = Color.Black
    )
}

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {
    val items = listOf(
        NavigationItem.Refrigerator,
        NavigationItem.Freezer,
        NavigationItem.Counter,
        NavigationItem.Pantry
    )
    Column(
        modifier = Modifier
            .background(color = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
                //.background(Color.Green),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_list),
                contentDescription = "",
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(10.dp)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(text = "Change View")
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {
            DrawerItem(
                item = it,
                selected = currentRoute == it.route,
                onItemClick = {
                    navController.navigate(it.route) {

                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) {
                                saveState = true
                            }
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                    scope.launch {
                        scaffoldState.drawerState.close()
                    }
                })
        }

        Spacer(modifier = Modifier.weight(1f))
/*
        Text(
            text = "dsydney",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )

 */
    }
}

@Composable
fun DrawerItem(item: NavigationItem, selected: Boolean, onItemClick: (NavigationItem) -> Unit) {
    val background = if(selected) R.color.purple_500 else android.R.color.transparent
    val textColor = if(selected) R.color.white else R.color.black
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .height(45.dp)
            .background(colorResource(id = background))
            .padding(start = 10.dp)
    ) {
        Image(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            colorFilter = ColorFilter.tint(Color.Black),
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .height(24.dp)
                .width(24.dp)
        )

        Spacer(modifier = Modifier.width(7.dp))

        Text(
            text = item.title,
            fontSize = 16.sp,
            color = colorResource(id = textColor) //Color.Black
        )
    }
}

@Composable
fun RefrigeratorScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Refrigerator Contents", fontFamily = FontFamily.Cursive, color = MaterialTheme.colors.onPrimary)
        }
        Box() {

            Image(
                painter = painterResource(id = R.drawable.womanlookingatcan),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Column(Modifier.verticalScroll(rememberScrollState())) {

                Product(image = R.drawable.eggs, name = "Eggs", expirationDate = "4/25/22")
                Product(image = R.drawable.milkclipart, name = "Milk", expirationDate = "4/29/22")
                Product(image = R.drawable.cheese, name = "Cheese", expirationDate = "4/29/22")
                Product(image = R.drawable.ham, name = "Ham", expirationDate = "5/5/22")
                Product(image = R.drawable.butter, name = "Butter", expirationDate = "4/24/22")
                Product(image = R.drawable.yogurrt, name = "Yogurt", expirationDate = "4/27/22")
                Product(image = R.drawable.orange_juice, name = "Orange Juice", expirationDate = "4/30/22")

                Spacer(modifier = Modifier.height(100.dp))

            }
        }
    }
}

@Composable
fun FreezerScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Freezer Contents", fontFamily = FontFamily.Cursive, color = MaterialTheme.colors.onPrimary)
        }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
        }
    }
}

@Composable
fun CounterScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Counter Contents", fontFamily = FontFamily.Cursive, color = MaterialTheme.colors.onPrimary)
        }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
        }
    }
}

@Composable
fun PantryScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colors.primary)
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Pantry Contents", fontFamily = FontFamily.Cursive, color = MaterialTheme.colors.onPrimary)
        }
        Column(Modifier.verticalScroll(rememberScrollState())) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 1")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 2")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Text(text = "Item 3")
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Refrigerator.route) {
        composable(NavigationItem.Refrigerator.route) {
            RefrigeratorScreen()
        }
        composable(NavigationItem.Freezer.route) {
            FreezerScreen()
        }
        composable(NavigationItem.Counter.route) {
            CounterScreen()
        }
        composable(NavigationItem.Pantry.route) {
            PantryScreen()
        }
    }
}

@Preview
@Composable
fun PreviewProductView() {
    ProductViewScreen()
}



/*
class ProductView : ComponentActivity() {
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
                    ProductViewScreen()                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ProductViewScreen()  {
    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    
    Scaffold(
        scaffoldState = scaffoldState,
        //topBar = { Topbar(scope = scope, scaffoldState = scaffoldState) },
        drawerContent = {
            Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
        }
    ) {
        DrawerNavigation(navController = navController)
    }
    
    Column {
        //TopAppBar
        TopAppBar(
            title = {Text("Product View")},
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "")
                }
            }
        )
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
/*
            Spacer(Modifier.height(60.dp))

            //Button - Delete item
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalArrangement = Arrangement.End
                    ) {
                Button(onClick = {
                    Toast.makeText(context, "DeleteItem", Toast.LENGTH_LONG).show()
                }) {
                    Text(text = "Delete Item")
                }
            }


 */

            //Nav Drawer
            //Drawer()
            //Different Locations, changes the Scrollable Box
            //Refrigerator, Freezer, Pantry, All, Expiring Tomorrow, and any other customizable locations
        } // End column - screen
    } // End column - TopAppBar and screen
}

@RequiresApi(Build.VERSION_CODES.N)
@Preview(showBackground = true)
@Composable
fun DefaultPreview5() {
    Expiration_DateTheme {
        ProductViewScreen()
    }
}

sealed class Screens(val title: String) {//, var icon: Int) {
    object Refrigerator: Screens("Refrigerator")//, R.drawable.ic_refrigerator)
    object Freezer: Screens("Freezer")//, R.drawable.ic_freezer)
    object Pantry: Screens("Pantry")//, R.drawable.ic_pantry)
    object Counter: Screens("Counter")//, R.drawable.ic_food)
}

private val screens = listOf(

    Screens.Refrigerator,
    Screens.Freezer,
    Screens.Pantry,
    Screens.Counter

)

@Composable
fun Drawer(scope: CoroutineScope, scaffoldState: ScaffoldState, navController: NavController) {

    Column() {

        // Icon

        screens.forEach {

            Row() {
                //it.icon
                //Spacer(modifier = Modifier.width(10.dp))
                Text(text = it.title)
            }
        }

    }

}

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun DrawerNavigation(navController: NavHostController) {
    NavHost(navController, startDestination = Screens.Refrigerator.title) {
        composable("refrigerator") {
            //go to refrigerator view
            ProductEntryScreen()
        }
        composable("freezer") {
            //go to freezer view
            ProductEntryScreen()
        }
        composable("counter") {
            //go to counter view
            ProductEntryScreen()
        }
        composable("pantry") {
            //go to pantry view
            ProductEntryScreen()
        }

    }
}

 */
