package adventofcode.day10

import adventofcode.CardinalDirection4
import adventofcode.CardinalDirection4.*
import adventofcode.Input
import adventofcode.Matrix
import adventofcode.Point
import org.jooq.lambda.Seq

fun main() {
    val lines = Input(Part02::class.java).toList()
    lines.replaceAll { ".$it." }
    lines.add(0, ".".repeat(lines[0].length))
    lines.add(".".repeat(lines[0].length))

    val matrix = Matrix.fromLines(lines)
    val start = Seq.seq(matrix).findFirst { it.value == 'S' }.get()

    val actualStartPipe = getActualStartPipe(matrix.neighbours(start))
    matrix[start.x, start.y] = actualStartPipe

    println(actualStartPipe)

    val mainLoop = findMainLoop(Point(start.x, start.y), matrix)

    println(mainLoop.size / 2)

}

fun findMainLoop(start: Point, matrix: Matrix) : List<Point> {
    var x = start.x
    var y = start.y
    var steps = 0
    val visited = LinkedHashSet<Point>()
    visited.add(Point(x, y))

    while(true) {

        var nextFound = false
        for (dir in getDirections(matrix[x, y])) {
            val newPoint = Point(x + dir.dx, y + dir.dy)
            if (!visited.contains(newPoint)) {
                visited.add(newPoint)
//                println("$newPoint: ${matrix[newPoint.x, newPoint.y]}")
                x = newPoint.x
                y = newPoint.y
                nextFound = true
                break
            }
        }

        steps++

        if (!nextFound) { //grįžta į pradžią
            break
        }

        if (x == start.x && y == start.y) {
            break
        }
    }

    return visited.toList()
}
fun getDirections(c: Char): List<CardinalDirection4> {
    return when(c) {
        '|' -> listOf(N, S)
        '-' -> listOf(E, W)
        'L' -> listOf(N, E)
        'J' -> listOf(N, W)
        '7' -> listOf(S, W)
        'F' -> listOf(S, E)
        '.' -> listOf()
        else -> throw IllegalArgumentException("Unknown direction: $c")
    }
}

fun getActualStartPipe(startSpace: Matrix) : Char {
    val north = getDirections(startSpace[1, 0])
    val east = getDirections(startSpace[2, 1])
    val south = getDirections(startSpace[1, 2])
    val west = getDirections(startSpace[0, 1])

    if (north.contains(S) && east.contains(W)) return 'L'
    if (north.contains(S) && west.contains(E)) return 'J'
    if (north.contains(S) && south.contains(N)) return '|'
    if (east.contains(W) && south.contains(N)) return 'F'
    if (east.contains(W) && west.contains(E)) return '-'
    if (south.contains(N) && west.contains(E)) return '7'

    throw IllegalArgumentException("Unknown start pipe")
}

class Part01 {
}