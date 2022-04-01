package com.revature.expiration_date

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revature.expiration_date.ui.theme.Expiration_DateTheme

/*
Notifications can trigger for 3 days prior, 1 day prior, day of expiration, and/or expired.
 */
@Composable
fun NotificationSettingsScreen() {
    // CHANGE TO API CALL
    var selectThreeDays: Boolean by rememberSaveable { mutableStateOf(false) }
    var selectOneDay: Boolean by rememberSaveable { mutableStateOf(false) }
    var selectDayOf: Boolean by rememberSaveable { mutableStateOf(true) }
    var selectAfter: Boolean by rememberSaveable { mutableStateOf(false) }

    val switchColorsSet = SwitchDefaults.colors(
        checkedThumbColor = MaterialTheme.colors.primary,
        uncheckedThumbColor = MaterialTheme.colors.primary,
        checkedTrackColor = MaterialTheme.colors.primaryVariant,
        uncheckedTrackColor = MaterialTheme.colors.secondaryVariant
    )
    val rowModifier = Modifier.fillMaxWidth(0.75f).padding(16.dp)

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
                        .fillMaxWidth(0.9f)
                        .padding(16.dp),
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
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Three days before",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = selectThreeDays,
                        onCheckedChange = { selectThreeDays = it },
                        colors = switchColorsSet
                    )
                }
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "One day before",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = selectOneDay,
                        onCheckedChange = { selectOneDay = it },
                        colors = switchColorsSet
                    )
                }
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "On expiration date",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = selectDayOf,
                        onCheckedChange = { selectDayOf = it },
                        colors = switchColorsSet
                    )
                }
                Row(
                    modifier = rowModifier,
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "After expiration date",
                        style = MaterialTheme.typography.h6
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = selectAfter,
                        onCheckedChange = { selectAfter = it },
                        colors = switchColorsSet
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    Expiration_DateTheme {
        NotificationSettingsScreen()
    }
}