package com.revature.expiration_date.productview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revature.expiration_date.R
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
fun Product(image: Int, name: String, expirationDate: String) {
    Card(
        elevation = 10.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(15.dp),
        //backgroundColor = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
                Image(
                    painter = painterResource(id = image),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.height(50.dp)
                )

            Spacer(modifier = Modifier.width(10.dp))

            Text(text = name)

            Spacer(modifier = Modifier.width(10.dp))

            Text(text = expirationDate)

            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "",
                tint = Color.Red,
                modifier = Modifier.size(30.dp)
                    .clickable(
                        onClick = {
                            //Delete Product
                        }
                    )
            )
        }
    }

}