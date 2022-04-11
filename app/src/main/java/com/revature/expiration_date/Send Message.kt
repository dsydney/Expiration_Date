package com.revature.expiration_date

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.revature.expiration_date.service.ExpiredNotification
import com.revature.expiration_date.service.INTENT_KEY_MESSAGE
import com.revature.expiration_date.viewmodel.ProductsViewModel

@Composable
fun SendMessageScreen() {
    val context = LocalContext.current

    val viewModel = viewModel<ProductsViewModel>()
    viewModel.listOfAllProducts(
        location = "Which location do you want to look at?",
        choices = listOf("Fridge", "Freezer", "Pantry", "All")
    )

    var selectExpired: Boolean by rememberSaveable { mutableStateOf(true) }
    var selectToday: Boolean by rememberSaveable { mutableStateOf(true) }
    var selectSoon: Boolean by rememberSaveable { mutableStateOf(false) }

    val switchColorsSet = SwitchDefaults.colors(
        checkedThumbColor = MaterialTheme.colors.primary,
        uncheckedThumbColor = MaterialTheme.colors.primary,
        checkedTrackColor = MaterialTheme.colors.primaryVariant,
        uncheckedTrackColor = MaterialTheme.colors.secondaryVariant
    )

    // FILTER INCOMING ITEMS
//    val itemsExpired = "${viewModel.items.items}\n"
//    val itemsToday = "${viewModel.items.items}\n"
//    val itemsSoon = "${viewModel.items.items}\n"

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Send Shopping List") }
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(24.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Include expired",
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = selectExpired,
                        onCheckedChange = { selectExpired = it },
                        colors = switchColorsSet
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Include expires today",
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = selectToday,
                        onCheckedChange = { selectToday = it },
                        colors = switchColorsSet
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Include expires soon",
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = selectSoon,
                        onCheckedChange = { selectSoon = it },
                        colors = switchColorsSet
                    )
                }

                Row(
                    modifier = Modifier.padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = {
                        if (selectExpired || selectToday || selectSoon) {
                            val sendIntent = Intent(Intent.ACTION_SEND).apply {
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    "Shopping List:\n" +
//                                        (if (selectExpired) "Expired: $itemsExpired\n" else "") +
//                                        (if (selectToday) "Going Bad: $itemsToday\n" else "") +
//                                        (if (selectSoon) "Replace: $itemsSoon\n" else "") +
                                        "${viewModel.items.items}\n" +
                                        "Thank you!"
                                )
                                type = "text/plain"
                            }

                            try {
                                context.startActivity(sendIntent)
                            } catch (ex: ActivityNotFoundException) {
                                Log.d("Activity", "Not found")
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Must select a list",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }) {
                        Text(
                            text = "Messenger",
                            style = MaterialTheme.typography.button
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    Button(onClick = {
                        Log.i("MESSAGE", "Button Notification clicked")
                        context.startNotification(viewModel.items.items.toString())
                    }) {
                        Text(
                            text = "Notification",
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            }
        }
    )
}

fun Context.startNotification(msg: String) {
    Log.i("MESSAGE", "called startNotification")
    val intent = Intent(this, ExpiredNotification::class.java)
    intent.putExtra(INTENT_KEY_MESSAGE, msg)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Log.i("MESSAGE", "Notification for SDK >= Version 0")
        this.startForegroundService(intent)
    } else {
        Log.i("MESSAGE", "Notification for SDK < Version 0")
        this.startService(intent)
    }
}