package com.example.higherlowerdeutschland.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.higherlowerdeutschland.domain.Stats
import com.example.higherlowerdeutschland.ui.theme.GermanyGold

@Composable
fun StatsScreen(stats: Stats, onBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(22.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Statistiken", fontSize = 34.sp, fontWeight = FontWeight.Black)
        StatRow("Highscore", stats.highscore.toString())
        StatRow("Spiele gespielt", stats.gamesPlayed.toString())
        StatRow("Beste Streak", stats.bestStreak.toString())
        StatRow("Richtige Antworten", stats.correctAnswers.toString())
        StatRow("Falsche Antworten", stats.wrongAnswers.toString())
        StatRow("Genauigkeit", "${stats.accuracyPercent} %")
        Text(
            text = "Zurück",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable(onClick = onBack)
                .padding(16.dp),
            color = GermanyGold,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun StatRow(label: String, value: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(label, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.74f))
            Text(value, fontSize = 22.sp, fontWeight = FontWeight.Black, color = GermanyGold)
        }
    }
}
