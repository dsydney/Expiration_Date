package com.revature.expiration_date

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.revature.expiration_date.ui.theme.Expiration_DateTheme

class BottomNavBar : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Expiration_DateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()

                    val startScreen = "add"

                    BottomNavBar(navController = navController, startScreen = startScreen)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomNavBar(navController: NavHostController, startScreen: String) {
    Scaffold(

        bottomBar = {

            BottomNavigationBar(
                items = listOf(

                    BottomNavItem(

                        name = "Add",
                        route = "add",
                        icon = Icons.Default.Add

                    ),

                    BottomNavItem(

                        name = "View",
                        route = "view",
                        icon = Icons.Default.Home

                    ),

                    BottomNavItem(

                        name = "Settings",
                        route = "settings",
                        icon = Icons.Default.Settings

                    )

                ),
                navController = navController,
                onItemClick = {

                    navController.navigate(it.route)

                }

            )

        }

    ) {

        Navigation(navController = navController, startScreen)

    }
}

@Composable
fun Navigation(

    navController: NavHostController,
    startScreen: String

) {

    //
    NavHost(navController = navController, startDestination = startScreen) {

        //
        composable("add") {

            //This will be our home screen
            ProductEntryScreen()

        }
        composable("view") {

            //This will be our chat screen
            ProductViewScreen()

        }
        composable("settings") {

            //This will be our settings screen
            NotificationSettingsScreen()

        }

    }

}

@ExperimentalMaterialApi
@Composable
fun BottomNavigationBar(

    //
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit

) {

    //
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(

        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp

    ) {

        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        if(item.badgeCount > 0) {

                            BadgeBox(

                                badgeContent = {

                                    Text(text = item.badgeCount.toString())

                                }

                            ) {

                                Icon(imageVector = item.icon, contentDescription = item.name)

                            }


                        } else {

                            Icon(imageVector = item.icon, contentDescription = item.name)

                        }
                        if(selected) {

                            Text(

                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp

                            )

                        }

                    }

                }
            )
        }

    }

}

data class BottomNavItem(

    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0

)


