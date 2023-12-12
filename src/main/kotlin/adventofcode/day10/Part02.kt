package adventofcode.day10

import adventofcode.CardinalDirection4
import adventofcode.Input
import adventofcode.Matrix
import adventofcode.Point
import com.google.common.collect.HashMultiset
import com.google.common.collect.Multiset
import org.jooq.lambda.Seq
import java.util.*

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

    val mainLoop = findMainLoop(Point(start.x, start.y), matrix).toSet()
    for (x in 0 until matrix.width) {
        for (y in 0 until matrix.height) {
            if (!mainLoop.contains(Point(x, y))) {
                matrix[x, y] = '.'
            }
        }
    }

    println()
    matrix.print(System.out)
    println()

    val voids = findVoids(matrix, mainLoop)
    val namedVoids = replaceUnconnectedPipes(matrix, voids)
    matrix.print(System.out)

    println()
    println("Void sizes:")
    for ((name, void) in namedVoids) {
        println("$name: ${void.size}")
    }
    println("Number of voids: ${voids.size}")
    println()

    var enclosed = 0
    for ((name, void) in namedVoids) {
        val start = void.first()
        val pipeConnections = getConnectionLine(matrix, start)
        val connected = pipeConnections.count(CardinalDirection4.S) % 2 == 0
        println("$name: $connected $pipeConnections")
        if (!connected) {
            enclosed += void.size
        }
    }

    println("Enclosed: $enclosed")

}

fun replaceUnconnectedPipes(matrix: Matrix, voids: List<Set<Point>>): SortedMap<Char, Set<Point>> {
    var c = 'a'
    val map = TreeMap<Char, Set<Point>>()
    for (void in voids) {
        for (point in void) {
            matrix[point.x, point.y] = c
        }
        map[c] = void
        do {
            c++
        } while (isPipe(c))
    }
    return map
}

fun findVoids(matrix: Matrix, mainLoop: Collection<Point>): List<Set<Point>> {
    val visited = mutableSetOf<Point>()
    visited.addAll(mainLoop)
    val voids = mutableListOf<Set<Point>>()
    for (x in 0 until matrix.width) {
        for (y in 0 until matrix.height) {
            val point = Point(x, y)
            if (!visited.contains(point)) {
                val void = findConnected(matrix, point, visited)
                voids.add(void)
                visited.addAll(void)
            }
        }
    }

    return voids
}

fun findConnected(matrix: Matrix, start: Point, visited: Set<Point>) : Set<Point> {
    val area = mutableSetOf<Point>()
    val Q: Queue<Point> = LinkedList()
    Q.add(start)
    while (!Q.isEmpty()) {
        val n = Q.remove()
        if (visited.contains(n) || area.contains(n)) {
            continue
        }
        area.add(n)
        for (cd in CardinalDirection4.entries) {
            val newPoint = n + cd
            if (matrix.contains(newPoint)) {
                Q.add(newPoint)
            }
        }
    }

    return area
}

fun getConnectionLine(matrix: Matrix, start: Point) : Multiset<CardinalDirection4> {
    if (start.x == 0 || start.y == 0 || start.x == matrix.width - 1 || start.y == matrix.height - 1) {
        return HashMultiset.create()
    }

    val pipeConnections = HashMultiset.create<CardinalDirection4>()
    for(x in start.x + 1 until matrix.width) {
        val c = matrix[x, start.y]
        if (!isPipe(c)) {
            continue
        }
        pipeConnections.addAll(getDirections(c))
    }
    return pipeConnections
}

fun isPipe(c: Char): Boolean {
    return c == '|' || c == '-' || c == 'L' || c == 'J' || c == '7' || c == 'F'
}

class Part02 {
}