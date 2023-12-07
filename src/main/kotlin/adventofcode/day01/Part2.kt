package adventofcode.day01

import adventofcode.Input
import java.util.*
import kotlin.streams.toList

private val otherDigits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
private val otherDigitsCP = otherDigits.stream().map { it.chars().toArray() }.toList()

fun main() {

    var sum = 0
    for (line in Input(Part2::class.java)) {

        val digits = mutableListOf<String>()

        val chars = line.chars().toArray()
        var i = 0
        outer@ while (i < chars.size) {
            if (Character.isDigit(chars[i])) {
                digits.add(chars[i].toChar().toString())
                i++
                continue
            }
            for ((j, value) in otherDigitsCP.withIndex()) {
                if (
                    try {
                        Arrays.mismatch(chars, i, i + value.size, value, 0, value.size) == -1
                    } catch (e: Exception) {
                        false
                    }
                ) {
                    digits.add((j + 1).toString())
                    i += value.size - 1
                    continue@outer
                }
            }
            i++
        }


        val number =
            Integer.parseInt(digits[0] + digits.last())
        println("$number $line")
        sum += number
    }
    println()
    println(sum)

}

class Part2 {

}