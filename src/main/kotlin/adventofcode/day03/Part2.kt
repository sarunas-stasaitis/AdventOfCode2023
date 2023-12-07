package adventofcode.day03

import adventofcode.Input

fun main() {
    val lines = Input(Part2::class.java).map { ".$it." }.toMutableList()
    lines.add(0, ".".repeat(lines[0].length))
    lines.add(".".repeat(lines[0].length))

    val matrix = Matrix(lines[0].length, lines.size)
    for (y in lines.indices) {
        for (x in 0 until lines[0].length) {
            matrix[x, y] = lines[y][x]
        }
    }

    val foundNumbers = mutableListOf<Int>()

    for (y in 0 until matrix.height) {
        for (x in 0 until matrix.width) {
            val char = matrix[x, y]

            if (char == '*') {
                val surrounding = matrix.submatrix(x - 1, y - 1, 3, 3)
                val numberCells = surrounding.filter { it.value.isDigit() }

                val surroundingNumbers = mutableSetOf<FoundNumber>()
                for (cell in numberCells) {
                    val foundNumber = findNumber(matrix, cell.x + x - 1, cell.y + y - 1)
                    surroundingNumbers.add(foundNumber)
                }
                val toList = surroundingNumbers.toList()
                if (toList.size == 2) {
                    foundNumbers.add(toList[0].value * toList[1].value)
                }

            }
        }
    }

    println()
    println(foundNumbers.sum())

}

data class FoundNumber (val xMin: Int, val xMax: Int, val value: Int)

fun findNumber(matrix: Matrix, x: Int, y: Int): FoundNumber {
    val digits = mutableListOf<Char>()

    var itx = x
    while (true) {
        val char = try {
            matrix[itx, y]
        } catch (e: IndexOutOfBoundsException) {
            break
        }
        if (char.isDigit()) {
            digits.add(char)
        } else {
            break
        }
        itx++
    }

    val xMax = itx

    itx = x-1
    while (true) {
        val char = try {
            matrix[itx, y]
        } catch (e: IndexOutOfBoundsException) {
            break
        }
        if (char.isDigit()) {
            digits.add(0, char)
        } else {
            break
        }
        itx--
    }

    return FoundNumber(itx, xMax, digits.joinToString("").toInt())
}

class Part2 {
}