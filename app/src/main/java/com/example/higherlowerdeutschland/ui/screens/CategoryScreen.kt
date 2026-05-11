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
import com.example.higherlowerdeutschland.domain.Category
import com.example.higherlowerdeutschland.ui.theme.GermanyGold

@Composable
fun CategoryScreen(
    onCategorySelected: (Category) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(22.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Kategorien",
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 34.sp,
            fontWeight = FontWeight.Black
        )
        Category.entries.forEach { category ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCategorySelected(category) },
                shape = RoundedCornerShape(22.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 7.dp)
            ) {
                Row(
                    modifier = Modifier.padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(text = category.icon, fontSize = 28.sp)
                    Text(text = category.label, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
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
