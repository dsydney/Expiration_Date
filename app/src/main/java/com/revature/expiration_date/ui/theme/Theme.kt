package com.revature.expiration_date.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Green600,
    primaryVariant = DarkGreen,
    secondary = Brown700,
    secondaryVariant = Brown300,
    // default dark mode values
    onPrimary = Black,
    onSecondary = White,
    // default dark mode values
)

private val LightColorPalette = lightColors(
    primary = Green900,
    primaryVariant = Green600,
    secondary = Brown700,
    secondaryVariant = Brown900,
    background = BlueGrey50,
    surface = BlueGrey100,
    // default error
    onPrimary = White,
    onSecondary = White,
    onBackground = Black,
    onSurface = Black,
    // default onError
)

@Composable
fun Expiration_DateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(colors.primary)

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}