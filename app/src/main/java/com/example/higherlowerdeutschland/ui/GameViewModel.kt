package com.example.higherlowerdeutschland.ui

import androidx.lifecycle.ViewModel
import com.example.higherlowerdeutschland.data.QuestionRepository
import com.example.higherlowerdeutschland.domain.AnswerResult
import com.example.higherlowerdeutschland.domain.Category
import com.example.higherlowerdeutschland.domain.GameUiState
import com.example.higherlowerdeutschland.domain.Stats
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class GameViewModel(
    private val repository: QuestionRepository = QuestionRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState: StateFlow<GameUiState> = _uiState.asStateFlow()

    init {
        startGame(Category.MIXED)
    }

    fun startGame(category: Category = Category.MIXED) {
        val (left, right) = repository.nextPair(category)
        _uiState.value = _uiState.value.copy(
            leftItem = left,
            rightItem = right,
            selectedCategory = category,
            score = 0,
            streak = 0,
            lives = 3,
            lastResult = null,
            isGameOver = false
        )
    }

    fun chooseHigher() = answer { right, left -> right.value >= left.value }

    fun chooseLower() = answer { right, left -> right.value < left.value }

    fun clearFeedbackAndContinue() {
        val current = _uiState.value
        if (current.isGameOver) return
        val category = current.selectedCategory
        val knownNext = current.rightItem ?: return
        val (_, challenger) = repository.nextPair(category)
        val fixedChallenger = if (challenger.id == knownNext.id) repository.nextPair(category).second else challenger
        _uiState.value = current.copy(
            leftItem = knownNext,
            rightItem = fixedChallenger,
            lastResult = null
        )
    }

    private fun answer(predicate: (right: com.example.higherlowerdeutschland.domain.QuizItem, left: com.example.higherlowerdeutschland.domain.QuizItem) -> Boolean) {
        val current = _uiState.value
        val left = current.leftItem ?: return
        val right = current.rightItem ?: return
        if (current.lastResult != null || current.isGameOver) return

        val correct = predicate(right, left)
        val updatedStats = current.stats.updateAfterAnswer(correct, current.score + if (correct) 1 else 0, current.streak + if (correct) 1 else 0)
        val newLives = if (correct) current.lives else current.lives - 1
        _uiState.value = current.copy(
            score = if (correct) current.score + 1 else current.score,
            streak = if (correct) current.streak + 1 else 0,
            lives = newLives,
            lastResult = if (correct) AnswerResult.CORRECT else AnswerResult.WRONG,
            isGameOver = newLives <= 0,
            stats = updatedStats
        )
    }

    private fun Stats.updateAfterAnswer(correct: Boolean, possibleScore: Int, possibleStreak: Int): Stats = copy(
        highscore = maxOf(highscore, possibleScore),
        bestStreak = maxOf(bestStreak, possibleStreak),
        correctAnswers = correctAnswers + if (correct) 1 else 0,
        wrongAnswers = wrongAnswers + if (correct) 0 else 1,
        gamesPlayed = gamesPlayed + if (!correct && _uiState.value.lives == 1) 1 else 0
    )
}
