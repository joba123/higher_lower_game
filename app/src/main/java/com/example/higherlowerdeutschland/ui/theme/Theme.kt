package com.example.higherlowerdeutschland.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = GermanyGold,
    secondary = GermanyRed,
    tertiary = SoftWhite,
    background = GermanBlack,
    surface = CardBlack,
    onPrimary = GermanBlack,
    onSecondary = SoftWhite,
    onBackground = SoftWhite,
    onSurface = SoftWhite
)

@Composable
fun HigherLowerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = AppTypography,
        content = content
    )
}
