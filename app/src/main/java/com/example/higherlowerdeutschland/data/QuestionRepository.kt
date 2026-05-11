package com.example.higherlowerdeutschland.data

import com.example.higherlowerdeutschland.domain.Category
import com.example.higherlowerdeutschland.domain.QuizItem

class QuestionRepository {
    private val items = listOf(
        QuizItem(1, "Berlin", "Einwohnerzahl", 3_755_000, "Menschen", Category.CITIES),
        QuizItem(2, "Hamburg", "Einwohnerzahl", 1_892_000, "Menschen", Category.CITIES),
        QuizItem(3, "München", "Einwohnerzahl", 1_512_000, "Menschen", Category.CITIES),
        QuizItem(4, "Köln", "Einwohnerzahl", 1_085_000, "Menschen", Category.CITIES),
        QuizItem(5, "Dortmund", "Einwohnerzahl", 594_000, "Menschen", Category.CITIES),
        QuizItem(6, "FC Bayern München", "Deutsche Meisterschaften", 33, "Titel", Category.FOOTBALL),
        QuizItem(7, "Borussia Dortmund", "Deutsche Meisterschaften", 8, "Titel", Category.FOOTBALL),
        QuizItem(8, "Hamburger SV", "Deutsche Meisterschaften", 6, "Titel", Category.FOOTBALL),
        QuizItem(9, "1. FC Nürnberg", "Deutsche Meisterschaften", 9, "Titel", Category.FOOTBALL),
        QuizItem(10, "Volkswagen Golf", "Neupreis grob", 28_000, "€", Category.CARS),
        QuizItem(11, "Mercedes-Benz S-Klasse", "Neupreis grob", 112_000, "€", Category.CARS),
        QuizItem(12, "Porsche 911", "Neupreis grob", 128_000, "€", Category.CARS),
        QuizItem(13, "Döner", "Durchschnittlicher Preis", 7, "€", Category.DAILY_LIFE),
        QuizItem(14, "Deutschlandticket", "Monatspreis", 58, "€", Category.DAILY_LIFE),
        QuizItem(15, "Kinoticket", "Durchschnittlicher Preis", 12, "€", Category.DAILY_LIFE),
        QuizItem(16, "Bayern", "Fläche", 70_542, "km²", Category.STATES),
        QuizItem(17, "Saarland", "Fläche", 2_570, "km²", Category.STATES),
        QuizItem(18, "Nordrhein-Westfalen", "Einwohnerzahl", 18_140_000, "Menschen", Category.STATES),
        QuizItem(19, "Volkswagen", "Mitarbeitende grob", 684_000, "Menschen", Category.COMPANIES),
        QuizItem(20, "SAP", "Mitarbeitende grob", 108_000, "Menschen", Category.COMPANIES),
        QuizItem(21, "Deutsche Bahn", "Mitarbeitende grob", 338_000, "Menschen", Category.COMPANIES)
    )

    fun categories(): List<Category> = Category.entries

    fun nextPair(category: Category = Category.MIXED): Pair<QuizItem, QuizItem> {
        val pool = if (category == Category.MIXED) items else items.filter { it.category == category }
        val safePool = pool.ifEmpty { items }
        val left = safePool.random()
        val right = safePool.filterNot { it.id == left.id }.ifEmpty { items.filterNot { it.id == left.id } }.random()
        return left to right
    }
}
