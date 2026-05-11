package com.example.higherlowerdeutschland.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.higherlowerdeutschland.ui.components.PrimaryGameButton
import com.example.higherlowerdeutschland.ui.theme.GermanyGold
import com.example.higherlowerdeutschland.ui.theme.GermanyRed

@Composable
fun HomeScreen(
    onEndlessMode: () -> Unit,
    onCategories: () -> Unit,
    onStats: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "▲", color = GermanyRed, fontSize = 30.sp)
            Text(
                text = "Mehr oder Weniger",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 38.sp,
                lineHeight = 42.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Deutschland",
                color = GermanyGold,
                fontSize = 30.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Das schnelle Higher-or-Lower-Quiz mit Städten, Fußball, Autos und Alltagsthemen.",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.72f),
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            PrimaryGameButton(text = "Endlosmodus", onClick = onEndlessMode)
            PrimaryGameButton(text = "Kategorien", onClick = onCategories)
            PrimaryGameButton(text = "Daily Challenge · Coming soon", onClick = {}, enabled = false)
            PrimaryGameButton(text = "Statistiken", onClick = onStats)
        }
    }
}
