package com.revature.expiration_date

import android.content.ActivityNotFoundException
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revature.expiration_date.ui.theme.Expiration_DateTheme

@Composable
fun SendMessageScreen() {
    var selectExpired: Boolean by rememberSaveable { mutableStateOf(true) }
    var selectToday: Boolean by rememberSaveable { mutableStateOf(true) }
    var selectSoon: Boolean by rememberSaveable { mutableStateOf(false) }

    val switchColorsSet = SwitchDefaults.colors(
        checkedThumbColor = MaterialTheme.colors.primary,
        uncheckedThumbColor = MaterialTheme.colors.primary,
        checkedTrackColor = MaterialTheme.colors.primaryVariant,
        uncheckedTrackColor = MaterialTheme.colors.secondaryVariant
    )
    val context = LocalContext.current

    // TEMP - replace with actual lists of items as formatted Strings
    val itemsExpired = "..."
    val itemsToday = "..."
    val itemsSoon = "..."

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
                                    "Shopping List:/n" +
                                        (if (selectExpired) "Expired: $itemsExpired/n" else "") +
                                        (if (selectToday) "Going Bad: $itemsToday/n" else "") +
                                        (if (selectSoon) "Replace: $itemsSoon/n" else "") +
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
                                "Select a list to send",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }) {
                        Text(
                            text = "Send",
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewSendMessageScreen() {
    Expiration_DateTheme {
        SendMessageScreen()
    }
}