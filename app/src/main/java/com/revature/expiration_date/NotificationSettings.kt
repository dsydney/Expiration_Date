package com.revature.expiration_date

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.revature.expiration_date.ui.theme.Expiration_DateTheme

class NotificationSettings : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Expiration_DateTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NotificationSettingsScreen()
                }
            }
        }
    }
}

@Composable
fun NotificationSettingsScreen() {
    //TopAppBar
    //List of all notifications
    //Settings to change notifications
    //   Who gets them (Phone numbers, emails), When (1 day, 3 days, past expiry, etc)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview6() {
    Expiration_DateTheme {
        NotificationSettingsScreen()
    }
}