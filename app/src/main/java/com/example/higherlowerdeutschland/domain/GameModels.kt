package com.example.higherlowerdeutschland.domain

data class Stats(
    val highscore: Int = 0,
    val gamesPlayed: Int = 0,
    val bestStreak: Int = 0,
    val correctAnswers: Int = 0,
    val wrongAnswers: Int = 0
) {
    val accuracyPercent: Int
        get() {
            val total = correctAnswers + wrongAnswers
            return if (total == 0) 0 else ((correctAnswers.toFloat() / total) * 100).toInt()
        }
}

data class GameUiState(
    val leftItem: QuizItem? = null,
    val rightItem: QuizItem? = null,
    val selectedCategory: Category = Category.MIXED,
    val score: Int = 0,
    val streak: Int = 0,
    val lives: Int = 3,
    val lastResult: AnswerResult? = null,
    val isGameOver: Boolean = false,
    val stats: Stats = Stats()
)

enum class AnswerResult {
    CORRECT,
    WRONG
}
