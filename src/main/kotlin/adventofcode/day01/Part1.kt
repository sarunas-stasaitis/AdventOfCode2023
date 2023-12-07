package adventofcode.day01

import adventofcode.Input
import kotlin.streams.toList

fun main() {

    var sum = 0
    for (line in Input(Part1::class.java)) {
        val digits = line.chars()
            .filter { Character.isDigit(it) }
            .toList()
        val number =
            Integer.parseInt(digits[0].toChar().toString() + digits.last().toChar().toString())
        println(number)
        sum += number
    }
    println()
    println(sum)

}

class Part1 {

}