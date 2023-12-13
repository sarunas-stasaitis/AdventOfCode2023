package adventofcode.day11

import adventofcode.Extensions.Companion.component1
import adventofcode.Extensions.Companion.component2
import adventofcode.Input
import adventofcode.Matrix
import adventofcode.Point
import org.jooq.lambda.Seq
import java.lang.System.out

fun main() {

    val matrixBits = Input(Part2::class.java).map { l -> l.toCharArray().toMutableList() }.toMutableList()

    var matrix = recreateMatrix(matrixBits)

    matrix.print(out)
    println()

    expandSpace(matrixBits)
    matrix = recreateMatrix(matrixBits)

    matrix.print(out)
    println()

    val galaxies = findGalaxies(matrix)
    for (galaxy in galaxies) {
        println(galaxy)
    }

    var totalDistance = 0
    Seq.crossJoin(galaxies, galaxies).forEach { (a, b) ->
        if (a != b) {
            val d = a.manhattanDistance(b)
            totalDistance += d
//            println("$a -> $b = $d")
        }
    }

    println()
    println(totalDistance / 2)

}

fun expandSpace(matrix: MutableList<MutableList<Char>>) {
    var x = 0
    while (x < matrix.size) {
        if (matrix[x].contains('#')) {
            x++
        } else {
            matrix.add(x, MutableList(matrix[0].size) { '.' })
            x+=2
        }
    }

    var y = 0
    while (y < matrix[0].size) {
        if (matrix.map { l -> l[y] }.contains('#')) {
            y++
        } else {
            matrix.forEach { l -> l.add(y, '.') }
            y+=2
        }
    }
}

fun recreateMatrix(matrix: List<List<Char>>): Matrix {
    val newMatrix = Array(matrix.size) { Array(matrix[0].size) { '.' } }
    for (x in matrix.indices) {
        for (y in 0 until matrix[0].size) {
            newMatrix[x][y] = matrix[x][y]
        }
    }
    return Matrix.fromLines(newMatrix.map { l -> l.joinToString("") })
}

fun findGalaxies(m : Matrix) : List<Point> {
    val galaxies = mutableListOf<Point>()
    for (x in 0 until m.width) {
        for (y in 0 until m.height) {
            if (m[x, y] == '#') {
                galaxies.add(Point(x, y))
            }
        }
    }
    return galaxies
}

class Part1 {
}