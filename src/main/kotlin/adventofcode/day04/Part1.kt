package adventofcode.day04

import adventofcode.Input


fun main() {
    var totalScore = 0

    for (line in Input(Part2::class.java).withIndex()) {
        val game = line.value.split(":")[1].split("|")
        val winningNumbers = game[0].split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() }
        val selectedNumbers = game[1].split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() }

        var score = 0
        for (n in selectedNumbers) {
            if (winningNumbers.contains(n)) {
                if (score == 0) {
                    score = 1
                } else {
                    score *= 2
                }
            }
        }
        totalScore += score

        println("Case #${line.index + 1}: $score")

    }

    println()
    println("Total score: $totalScore")
}

class Part1 {
}