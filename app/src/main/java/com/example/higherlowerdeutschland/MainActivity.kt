package com.example.higherlowerdeutschland

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.higherlowerdeutschland.domain.Category
import com.example.higherlowerdeutschland.ui.GameViewModel
import com.example.higherlowerdeutschland.ui.screens.CategoryScreen
import com.example.higherlowerdeutschland.ui.screens.GameScreen
import com.example.higherlowerdeutschland.ui.screens.HomeScreen
import com.example.higherlowerdeutschland.ui.screens.StatsScreen
import com.example.higherlowerdeutschland.ui.theme.HigherLowerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HigherLowerTheme {
                HigherLowerApp()
            }
        }
    }
}

private enum class AppScreen {
    HOME,
    GAME,
    CATEGORIES,
    STATS
}

@Composable
fun HigherLowerApp(viewModel: GameViewModel = viewModel()) {
    var currentScreen by remember { mutableStateOf(AppScreen.HOME) }
    val state by viewModel.uiState.collectAsState()

    when (currentScreen) {
        AppScreen.HOME -> HomeScreen(
            onEndlessMode = {
                viewModel.startGame(Category.MIXED)
                currentScreen = AppScreen.GAME
            },
            onCategories = { currentScreen = AppScreen.CATEGORIES },
            onStats = { currentScreen = AppScreen.STATS }
        )

        AppScreen.GAME -> GameScreen(
            state = state,
            onHigher = viewModel::chooseHigher,
            onLower = viewModel::chooseLower,
            onNext = viewModel::clearFeedbackAndContinue,
            onRestart = { viewModel.startGame(state.selectedCategory) },
            onBack = { currentScreen = AppScreen.HOME }
        )

        AppScreen.CATEGORIES -> CategoryScreen(
            onCategorySelected = { category ->
                viewModel.startGame(category)
                currentScreen = AppScreen.GAME
            },
            onBack = { currentScreen = AppScreen.HOME }
        )

        AppScreen.STATS -> StatsScreen(
            stats = state.stats,
            onBack = { currentScreen = AppScreen.HOME }
        )
    }
}
