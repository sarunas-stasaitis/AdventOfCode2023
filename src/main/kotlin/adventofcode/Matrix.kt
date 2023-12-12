package adventofcode

import java.io.PrintStream
import java.io.PrintWriter

class Matrix (val width: Int, val height: Int) : Iterable<Matrix.Cell> {

    companion object {
        fun fromLines(lines: List<String>): Matrix {
            val matrix = Matrix(lines[0].length, lines.size)
            for (y in lines.indices) {
                for (x in 0 until lines[0].length) {
                    matrix[x, y] = lines[y][x]
                }
            }
            return matrix
        }
    }

    val matrix = Array(width) { Array(height) { '0' } }

    operator fun get(x: Int, y: Int) = matrix[x][y]

    fun relativeGet(x: Int, y: Int, dx: Int, dy: Int): Char {
        return matrix[x + dx][y + dy]
    }

    fun relativeGet(x: Int, y: Int, direction: CardinalDirection4): Char {
        return relativeGet(x, y, direction.dx, direction.dy)
    }

    operator fun set(x: Int, y: Int, value: Char) {
        matrix[x][y] = value
    }

    fun neighbours(cell: Cell): Matrix = neighbours(cell.x, cell.y)

    fun neighbours(x: Int, y: Int) : Matrix {
        return doSubmatrix(x - 1, y - 1, 3, 3)
    }

    fun submatrix(x: Int, y: Int, width: Int, height: Int): Matrix {
        var rWidth = width
        var rHeight = height

        val rx = if (x < 0) {rWidth--;0} else x
        val ry = if (y < 0) {rHeight--;0} else y

        if (rx + rWidth > this.width) rWidth = this.width - rx
        if (ry + rHeight > this.height) rHeight = this.height - ry

        return doSubmatrix(rx, ry, rWidth, rHeight)
    }

    private fun doSubmatrix(x: Int, y: Int, width: Int, height: Int): Matrix {
        val submatrix = Matrix(width, height)
        for (y1 in y until y + height) {
            for (x1 in x until x + width) {
                submatrix[x1 - x, y1 - y] = this[x1, y1]
            }
        }
        return submatrix
    }

    fun copy() : Matrix {
        val copy = Matrix(width, height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                copy[x, y] = this[x, y]
            }
        }
        return copy
    }

    fun contains(point: Point): Boolean {
        return contains(point.x, point.y)
    }

    fun contains(x: Int, y: Int): Boolean {
        return x in 0..<width && y in 0..<height
    }

    fun print(out: PrintStream) {
        for (y in 0 until height) {
            for (x in 0 until width) {
                out.print(matrix[x][y])
            }
            out.println()
        }
    }

    override fun toString(): String {
        val sb = StringBuilder()
        for (y in 0 until height) {
            for (x in 0 until width) {
                sb.append(matrix[x][y])
            }
            sb.append("\n")
        }
        sb.setLength(sb.length - 1)
        return sb.toString()
    }

    fun anyMatch(predicate: (Int, Int, Char) -> Boolean): Boolean {
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (predicate(x, y, matrix[x][y])) {
                    return true
                }
            }
        }
        return false
    }


    override fun iterator(): Iterator<Cell> {
        return object : Iterator<Cell> {
            var x = 0
            var y = 0

            override fun hasNext(): Boolean {
                return y < height
            }

            override fun next(): Cell {
                val cell = Cell(x, y, matrix[x][y])
                x++
                if (x == width) {
                    x = 0
                    y++
                }
                return cell
            }
        }
    }

    data class Cell(val x: Int, val y: Int, val value: Char)


}