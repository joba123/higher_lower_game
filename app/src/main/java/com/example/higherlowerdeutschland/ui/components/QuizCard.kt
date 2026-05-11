package com.example.higherlowerdeutschland.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.higherlowerdeutschland.domain.AnswerResult
import com.example.higherlowerdeutschland.domain.QuizItem
import com.example.higherlowerdeutschland.ui.theme.GermanyGold
import com.example.higherlowerdeutschland.ui.theme.GermanyRed
import com.example.higherlowerdeutschland.ui.theme.SuccessGreen

@Composable
fun QuizCard(
    item: QuizItem?,
    revealValue: Boolean,
    modifier: Modifier = Modifier,
    feedback: AnswerResult? = null
) {
    val borderColor by animateColorAsState(
        targetValue = when (feedback) {
            AnswerResult.CORRECT -> SuccessGreen
            AnswerResult.WRONG -> GermanyRed
            null -> GermanyGold
        },
        label = "feedbackColor"
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(220.dp),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(borderColor.copy(alpha = 0.08f))
                .padding(3.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(25.dp))
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(22.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = item?.category?.let { "${it.icon} ${it.label}" }.orEmpty(),
                    color = GermanyGold,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = item?.title.orEmpty(),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = 28.sp,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = item?.subtitle.orEmpty(),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.68f),
                        fontSize = 15.sp,
                        textAlign = TextAlign.Center
                    )
                }
                Text(
                    text = if (revealValue) item?.formattedValue.orEmpty() else "?",
                    color = if (revealValue) GermanyGold else MaterialTheme.colorScheme.onSurface,
                    fontSize = if (revealValue) 24.sp else 52.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
