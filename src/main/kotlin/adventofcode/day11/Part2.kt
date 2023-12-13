package adventofcode.day11

import adventofcode.Extensions.Companion.component1
import adventofcode.Extensions.Companion.component2
import adventofcode.Input
import adventofcode.Matrix
import adventofcode.Point
import com.google.common.collect.Range
import org.jooq.lambda.Seq

fun main() {

    val matrixBits = Input(Part2::class.java).map { l -> l.toCharArray().toMutableList() }.toMutableList()

    var matrix = recreateMatrix(matrixBits)

//    matrix.print(out)
    println()

    val emptyCols = findEmptyCols(matrix)
    val emptyRows = findEmptyRows(matrix)

    val galaxies = findGalaxies(matrix)
    for (galaxy in galaxies) {
        println(galaxy)
    }

    val growAmount = 1000000 - 1

    var totalDistance = 0L

    val visited = HashSet<Set<Point>>()

    Seq.crossJoin(galaxies, galaxies).forEach { (a, b) ->
        if (a != b && !visited.contains(setOf(a, b))) {
            var d = a.manhattanDistance(b)
            val xRange = Range.encloseAll(listOf(a.x, b.x))
            for (e in emptyCols) {
                if (e in xRange) {
                    d+=growAmount
                }
            }
            val yRange = Range.encloseAll(listOf(a.y, b.y))
            for (e in emptyRows) {
                if (e in yRange) {
                    d+=growAmount
                }
            }

            totalDistance += d

            visited.add(setOf(a, b))
        }
    }

    println()
    println(totalDistance)

}

fun findEmptyCols(m: Matrix) : List<Int> {
    val emptyCols = ArrayList<Int>()
    for (x in 0 until m.width) {
        if (m.column(x).all { c -> c == '.' }) {
            emptyCols.add(x)
        }
    }
    return emptyCols
}

fun findEmptyRows(m: Matrix) : List<Int> {
    val emptyRows = ArrayList<Int>()
    for (y in 0 until m.height) {
        if (m.row(y).all { c -> c == '.' }) {
            emptyRows.add(y)
        }
    }
    return emptyRows
}

class Part2 {
}