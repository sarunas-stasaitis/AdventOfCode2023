package adventofcode.day04

import adventofcode.Input


fun main() {
    val cards = Input(Part2::class.java).mapIndexed { i, line ->  Card(i + 1, line) }

    for (c in cards.withIndex()) {
        val i = c.index
        val card = c.value
        for (n in 1 until card.wins + 1) {
            cards[i + n].count += card.count
        }
    }

    val score = cards.sumOf { it.count }

    for (c in cards) {
        println(c)
    }
    println()
    println("Total score: $score")
}

class Part2 {
}