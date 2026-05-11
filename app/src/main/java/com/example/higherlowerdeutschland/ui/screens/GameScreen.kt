package com.example.higherlowerdeutschland.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.higherlowerdeutschland.domain.AnswerResult
import com.example.higherlowerdeutschland.domain.GameUiState
import com.example.higherlowerdeutschland.ui.components.PrimaryGameButton
import com.example.higherlowerdeutschland.ui.components.QuizCard
import com.example.higherlowerdeutschland.ui.theme.GermanyGold
import kotlinx.coroutines.delay

@Composable
fun GameScreen(
    state: GameUiState,
    onHigher: () -> Unit,
    onLower: () -> Unit,
    onNext: () -> Unit,
    onRestart: () -> Unit,
    onBack: () -> Unit
) {
    LaunchedEffect(state.lastResult, state.isGameOver) {
        if (state.lastResult != null && !state.isGameOver) {
            delay(950)
            onNext()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Score ${state.score} · Streak ${state.streak}", fontWeight = FontWeight.Bold)
            Text(text = "❤".repeat(state.lives), color = GermanyGold, fontSize = 22.sp)
        }

        QuizCard(item = state.leftItem, revealValue = true)
        Text(
            text = "Hat ${state.rightItem?.title.orEmpty()} einen höheren oder niedrigeren Wert?",
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.74f),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
        QuizCard(item = state.rightItem, revealValue = state.lastResult != null, feedback = state.lastResult)

        AnimatedVisibility(
            visible = state.lastResult != null,
            enter = fadeIn(animationSpec = tween(180)),
            exit = fadeOut(animationSpec = tween(180))
        ) {
            Text(
                text = if (state.lastResult == AnswerResult.CORRECT) "Richtig!" else "Leider falsch!",
                modifier = Modifier.fillMaxWidth(),
                color = if (state.lastResult == AnswerResult.CORRECT) GermanyGold else MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            PrimaryGameButton(
                text = "Weniger",
                onClick = onLower,
                enabled = state.lastResult == null,
                modifier = Modifier.weight(1f)
            )
            PrimaryGameButton(
                text = "Höher",
                onClick = onHigher,
                enabled = state.lastResult == null,
                modifier = Modifier.weight(1f)
            )
        }
    }

    if (state.isGameOver) {
        AlertDialog(
            onDismissRequest = onBack,
            title = { Text("Game over") },
            text = { Text("Dein Score: ${state.score}\nBeste Streak: ${state.stats.bestStreak}") },
            confirmButton = { TextButton(onClick = onRestart) { Text("Nochmal") } },
            dismissButton = { TextButton(onClick = onBack) { Text("Home") } }
        )
    }
}
