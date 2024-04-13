package com.gerhard.cs50x.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember

@Composable
fun TwitterAppTheme(
    typography: TwitterAppTypography = TwitterAppTheme.typography,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = if (darkTheme) darkColors() else lightColors()
    val rememberedColors = remember {
        colors.copy()
    }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides typography,
        content = content
    )
}

object TwitterAppTheme {
    val colors: TwitterAppColors
        @Composable
        get() = LocalColors.current

    val typography: TwitterAppTypography
        @Composable
        get() = LocalTypography.current
}
