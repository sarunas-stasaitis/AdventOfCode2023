package adventofcode.day02

import adventofcode.Input
import kotlin.math.max


fun main() {

    val rMax = 12
    val gMax = 13
    val bMax = 14

    val gamePOWERs = mutableListOf<Int>()

    games@ for (line in Input(Part2::class.java)) {

        var r = 0
        var g = 0
        var b = 0

        val split = line.split(": ")
        val gameId = split[0].split(" ")[1].toInt()
        for(pull in split[1].split("; ")) {

            for(it in pull.split(", ")) {
                val pullPart = it.split(" ")
                when(pullPart[1]) {
                    "red" -> r = max(r, pullPart[0].toInt())
                    "green" -> g = max(g, pullPart[0].toInt())
                    "blue" -> b = max(b, pullPart[0].toInt())
                }
            }
        }

        val POWER = r * g * b
        println("Game $gameId has POWER $POWER")
        gamePOWERs.add(POWER)

    }

    println("Sum of game POWERs: ${gamePOWERs.sum()}")
}

class Part2 {
}