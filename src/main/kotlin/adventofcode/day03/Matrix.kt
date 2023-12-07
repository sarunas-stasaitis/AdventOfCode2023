package adventofcode.day03

class Matrix (val width: Int, val height: Int) : Iterable<Matrix.Cell> {

    val matrix = Array(width) { Array(height) { '0' } }

    operator fun get(x: Int, y: Int) = matrix[x][y]

    operator fun set(x: Int, y: Int, value: Char) {
        matrix[x][y] = value
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