package com.gerhard.cs50x.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class TwitterAppColors(
    bg01: Color,
    bg02: Color,
    bg03: Color,
    text01: Color,
    text02: Color,
    aux01: Color,
    aux02: Color,
    aux03: Color,
    accent01: Color,
    accent02: Color,
    accent03: Color,
    accent04: Color,
    accent05: Color,
    isLight: Boolean
) {
    var bg01 by mutableStateOf(bg01)
        private set

    var bg02 by mutableStateOf(bg02)
        private set

    var bg03 by mutableStateOf(bg03)
        private set

    var text01 by mutableStateOf(text01)
        private set

    var text02 by mutableStateOf(text02)
        private set

    var aux01 by mutableStateOf(aux01)
        private set

    var aux02 by mutableStateOf(aux02)
        private set

    var aux03 by mutableStateOf(aux03)
        private set

    var accent01 by mutableStateOf(accent01)
        private set

    var accent02 by mutableStateOf(accent02)
        private set

    var accent03 by mutableStateOf(accent03)
        private set

    var accent04 by mutableStateOf(accent04)
        private set

    var accent05 by mutableStateOf(accent05)
        private set

    var isLight by mutableStateOf(isLight)
        private set

    fun copy(
        background01: Color = this.bg01,
        background02: Color = this.bg02,
        background03: Color = this.bg03,
        text01: Color = this.text01,
        text02: Color = this.text02,
        aux01: Color = this.aux01,
        aux02: Color = this.aux02,
        aux03: Color = this.aux03,
        accent01: Color = this.accent01,
        accent02: Color = this.accent02,
        accent03: Color = this.accent03,
        accent04: Color = this.accent04,
        accent05: Color = this.accent05,
        isLight: Boolean = this.isLight
    ) = TwitterAppColors(
        background01,
        background02,
        background03,
        text01,
        text02,
        aux01,
        aux02,
        aux03,
        accent01,
        accent02,
        accent03,
        accent04,
        accent05,
        isLight
    )

    fun updateColorsFrom(other: TwitterAppColors) {
        bg01 = other.bg01
        bg02 = other.bg02
        bg03 = other.bg03
        text01 = other.text01
        text02 = other.text02
        aux01 = other.aux01
        aux02 = other.aux02
        aux03 = other.aux03
        accent01 = other.accent01
        accent02 = other.accent02
        accent03 = other.accent03
        accent04 = other.accent04
        accent05 = other.accent05
        isLight = other.isLight
    }
}

fun lightColors(
    background01: Color = Color(0xFFFAFAFA),
    background02: Color = Color(0xFF616161),
    background03: Color = Color(0xFFEEEEEE),
    text01: Color = Color(0xFF141414),
    text02: Color = Color(0xFF888888),
    aux01: Color = Color(0xFFA79A89),
    aux02: Color = Color(0xFFC7BEB4),
    aux03: Color = Color(0xFFDDC1A0),
    accent01: Color = Color(0xFFEC4040),
    accent02: Color = Color(0xFFF4A14A),
    accent03: Color = Color(0xFF68A0BF),
    accent04: Color = Color(0xFFFFE5A4),
    accent05: Color = Color(0xFF4BB161)
) = TwitterAppColors(
    bg01 = background01,
    bg02 = background02,
    bg03 = background03,
    text01 = text01,
    text02 = text02,
    aux01 = aux01,
    aux02 = aux02,
    aux03 = aux03,
    accent01 = accent01,
    accent02 = accent02,
    accent03 = accent03,
    accent04 = accent04,
    accent05 = accent05,
    isLight = true
)

fun darkColors(
    background01: Color = Color(0xFF212121),
    background02: Color = Color(0xFF424242),
    background03: Color = Color(0xFF616161),
    text01: Color = Color(0xFFFFFFFF),
    text02: Color = Color(0xFFAAAAAA),
    aux01: Color = Color(0xFFC7BEB4),
    aux02: Color = Color(0xFFA79A89),
    aux03: Color = Color(0xFF9E9E9E),
    accent01: Color = Color(0xFFFFAF5A),
    accent02: Color = Color(0xFFFFAF5A),
    accent03: Color = Color(0xFFBAD3E1),
    accent04: Color = Color(0xFF183F48),
    accent05: Color = Color(0xFF54C76D)
) = TwitterAppColors(
    bg01 = background01,
    bg02 = background02,
    bg03 = background03,
    text01 = text01,
    text02 = text02,
    aux01 = aux01,
    aux02 = aux02,
    aux03 = aux03,
    accent01 = accent01,
    accent02 = accent02,
    accent03 = accent03,
    accent04 = accent04,
    accent05 = accent05,
    isLight = false
)

internal val LocalColors = staticCompositionLocalOf { lightColors() }
