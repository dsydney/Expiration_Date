package com.revature.expiration_date.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = CutCornerShape(
        topStart = 0.dp,
        topEnd = 128.dp,
        bottomStart = 0.dp,
        bottomEnd = 0.dp
    )
)