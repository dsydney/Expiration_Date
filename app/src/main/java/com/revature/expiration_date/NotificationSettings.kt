package com.revature.expiration_date

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.revature.expiration_date.data.SettingsManager
import com.revature.expiration_date.ui.theme.Expiration_DateTheme
import com.revature.expiration_date.viewmodel.ProductsViewModel
import com.revature.expiration_date.viewmodel.SettingsViewModel
import kotlinx.coroutines.launch

/*
Notifications can trigger for 3 days prior, 1 day prior, day of expiration, and/or expired.
 */
@Composable
fun NotificationSettingsScreen(navController: NavHostController) {
    val context = LocalContext.current
    val viewModel = viewModel<ProductsViewModel>()
    val scope = rememberCoroutineScope()
    val dataStore = SettingsManager(context)

    val isThree = dataStore.getThreeDay.collectAsState(initial = false)
    val isOne = dataStore.getOneDay.collectAsState(initial = false)
    val isZero = dataStore.getZeroDay.collectAsState(initial = true)
    val isAfter = dataStore.getAfterDay.collectAsState(initial = false)

    var selectThreeDays: Boolean by rememberSaveable { mutableStateOf(isThree.value) }
    var selectOneDay: Boolean by rememberSaveable { mutableStateOf(isOne.value) }
    var selectDayOf: Boolean by rememberSaveable { mutableStateOf(isZero.value) }
    var selectAfter: Boolean by rememberSaveable { mutableStateOf(isAfter.value) }

    val switchColorsSet = SwitchDefaults.colors(
        checkedThumbColor = MaterialTheme.colors.primary,
        uncheckedThumbColor = MaterialTheme.colors.primary,
        checkedTrackColor = MaterialTheme.colors.primaryVariant,
        uncheckedTrackColor = MaterialTheme.colors.secondaryVariant
    )

    val rowModifier = Modifier
        .fillMaxWidth(0.75f)
        .padding(16.dp)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notification Settings") }
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Remind me:",
                        style = MaterialTheme.typography.h5
                    )
                }
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = Arrangement.SpaceBetween, //End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Three days before",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = selectThreeDays,
                        onCheckedChange = {
                            selectThreeDays = it
                            scope.launch { dataStore.saveThreeDay(it) }
                        },
                        colors = switchColorsSet
                    )
                }
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = Arrangement.SpaceBetween, //End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "One day before",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = selectOneDay,
                        onCheckedChange = {
                            selectOneDay = it
                            scope.launch { dataStore.saveOneDay(it) }
                        },
                        colors = switchColorsSet
                    )
                }
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = Arrangement.SpaceBetween, //End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "On expiration date",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = selectDayOf,
                        onCheckedChange = {
                            selectDayOf = it
                            scope.launch { dataStore.saveZeroDay(it) }
                        },
                        colors = switchColorsSet
                    )
                }
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = Arrangement.SpaceBetween, //End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "After expiration date",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = selectAfter,
                        onCheckedChange = {
                            selectAfter = it
                            scope.launch { dataStore.saveAfterDay(it) }
                        },
                        colors = switchColorsSet
                    )
                }
                Spacer(Modifier.height(32.dp))
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                        navController.navigate("message")
                    }
                    ) {
                        Text("Send as Message")
                    }
                }
            }
        }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewNotificationSettingsScreen() {
//    Expiration_DateTheme {
//        NotificationSettingsScreen()
//    }
//}