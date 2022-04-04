package com.revature.expiration_date.productview

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revature.expiration_date.productview.ui.theme.Expiration_DateTheme

class Product : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Expiration_DateTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    //
                }
            }
        }
    }
}

@Composable
fun testRowItem() {
    Text(text = "TESTTESTTESTTESTTESTTESTTESTTESTTEST")
}

@Composable
fun Product(name: String, expirationDate: String) {
    Card(
        elevation = 10.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(text = name)

            Spacer(modifier = Modifier.width(10.dp))

            Text(text = expirationDate)
        }
    }

}