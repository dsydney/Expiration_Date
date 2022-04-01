package com.revature.expiration_date.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.revature.expiration_date.R

/**
 * https://fonts.google.com/specimen/Merriweather
 */
private val Merriweather = FontFamily(
    Font(R.font.merriweather),
    Font(R.font.merriweather_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.merriweather_bold, FontWeight.Bold)
)

val Typography = Typography(
    defaultFontFamily = Merriweather,
//    body1 = TextStyle(
//        fontFamily = Merriweather,
//        fontWeight = FontWeight.Normal,
//        fontSize = 16.sp
//    ),
//    button = TextStyle(
//        fontFamily = Merriweather,
//        fontWeight = FontWeight.W500,
//        fontSize = 14.sp
//    ),
//    caption = TextStyle(
//        fontFamily = Merriweather,
//        fontWeight = FontWeight.Normal,
//        fontSize = 12.sp
//    )
)