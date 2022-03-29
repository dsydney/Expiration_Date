package com.revature.expiration_date

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revature.expiration_date.ui.theme.Expiration_DateTheme

//class NotificationSettings : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            Expiration_DateTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    NotificationSettingsScreen()
//                }
//            }
//        }
//    }
//}

@Composable
fun NotificationSettingsScreen() {
    // ADD state variables for each setting

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
                //List of all notifications
                //Settings to change notifications
                //   Who gets them (Phone numbers, emails), When (1 day, 3 days, past expiry, etc)

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Setting 1",
                        style = MaterialTheme.typography.h5
                    )
                    Spacer(Modifier.width(24.dp))
                    Switch(
                        checked = false,
                        onCheckedChange = {  },
//                        colors = switchColorsSet
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