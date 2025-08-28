package com.example.ppm_lab5.ui.theme

import androidx.compose.ui.graphics.Color

sealed class ThemeColors(
    val background: Color,
    val surface: Color,
    val primary: Color,
    val text: Color,
    val secondary: Color
) {
    object Night : ThemeColors(
        background = Color(0xFF202124),
        surface = Color(0xFF000000),
        primary = Color(0xFFFFFFFF),
        text = Color(0xFFFFFFFF),
        secondary = Color(0xFF3D4042)

    )

    object Day : ThemeColors(
        background = Color(0XFFFFFFFF),
        surface = Color(0XFFFFFFFF),
        primary = Color(0xFFF5F4F4),
        text = Color(0XFF000000),
        secondary = Color(0xFF03DAC5)
    )

}

