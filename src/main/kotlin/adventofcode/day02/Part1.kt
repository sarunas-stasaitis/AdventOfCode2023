package adventofcode.day02

import adventofcode.Input


fun main() {

    val rMax = 12
    val gMax = 13
    val bMax = 14

    val possibleGames = mutableListOf<Int>()

    games@ for (line in Input(Part2::class.java)) {

        val split = line.split(": ")
        val gameId = split[0].split(" ")[1].toInt()
        for(pull in split[1].split("; ")) {
            var r = 0
            var g = 0
            var b = 0

            for(it in pull.split(", ")) {
                val pullPart = it.split(" ")
                when(pullPart[1]) {
                    "red" -> r = pullPart[0].toInt()
                    "green" -> g = pullPart[0].toInt()
                    "blue" -> b = pullPart[0].toInt()
                }
            }

            if (r > rMax || g > gMax || b > bMax) {
                println("Game $gameId is not possible")
                continue@games
            }
        }

        println("Game $gameId is possible")
        possibleGames.add(gameId)

    }

    println("Sum of possible games: ${possibleGames.sum()}")
}

class Part1 {
}