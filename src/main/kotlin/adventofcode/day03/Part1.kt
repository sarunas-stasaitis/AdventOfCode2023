package adventofcode.day03

import adventofcode.Input
import adventofcode.Matrix

fun main() {
    val lines = Input(Part2::class.java).toList()

    val matrix = Matrix(lines[0].length, lines.size)
    for (y in lines.indices) {
        for (x in 0 until lines[0].length) {
            matrix[x, y] = lines[y][x]
        }
    }

    val foundNumbers = mutableListOf<Int>()
    val number = mutableListOf<Char>()
    for (y in 0 until matrix.height) {
        for (x in 0 until matrix.width) {
            val char = matrix[x, y]
            var processNumber = false

            if (char.isDigit()) {
                number.add(char)
            } else if (number.isNotEmpty()) {
                processNumber = true
            }

            if (x == matrix.width - 1 && number.isNotEmpty()) {
                processNumber = true
            }

            if (processNumber) {
                val l = number.size

                val submatrix = matrix.submatrix(x - l - 1, y - 1, l + 2, 3)
                val anyMatch = submatrix.anyMatch { _, _, c -> !c.isDigit() && c != '.' }
                if (anyMatch) {
                    foundNumbers.add(number.joinToString("").toInt())
                }

                println("   ${number.joinToString("")}\n-----------------")
                println(submatrix)
                println("   $anyMatch\n")

                number.clear()
            }
        }
    }

    println()
    println(foundNumbers.sum())


}

class Part1 {
}