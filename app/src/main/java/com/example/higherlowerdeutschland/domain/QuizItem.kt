package com.example.higherlowerdeutschland.domain

import java.text.NumberFormat
import java.util.Locale

data class QuizItem(
    val id: Int,
    val title: String,
    val subtitle: String,
    val value: Int,
    val unit: String,
    val category: Category
) {
    val formattedValue: String
        get() = "${value.toGermanNumber()} $unit"
}

fun Int.toGermanNumber(): String = NumberFormat.getIntegerInstance(Locale.GERMANY).format(this)
