package com.revature.expiration_date

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
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

    Scaffold(
        topBar = {
            TopAppBar(
                content = { Text("Send list to messenger") }
            )
        },
        content = {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Text("Include expired")
                    Switch(
                        checked = selectExpired,
                        onCheckedChange = {
                            selectExpired = it
                        },
                        colors = switchColorsSet
                    )
                }
                Row {
                    Text("Include expires today")
                    Switch(
                        checked = selectToday,
                        onCheckedChange = {
                            selectToday = it
                        },
                        colors = switchColorsSet
                    )
                }
                Row {
                    Text("Include expires soon")
                    Switch(
                        checked = selectSoon,
                        onCheckedChange = {
                            selectSoon = it
                        },
                        colors = switchColorsSet
                    )
                }
                Row {
                    Button(onClick = { /*TODO*/ }) {
                        Text("Open in Messenger")
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