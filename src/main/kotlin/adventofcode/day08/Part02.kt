package adventofcode.day08

import adventofcode.Input
import java.math.BigInteger

fun main() {
    val inputLines = Input(Part02::class.java).toList()

    val instructions = inputLines[0].toCharArray().map { it.toString() }

    val nodes = mutableMapOf<String, Node>()

    for (i in 2 until inputLines.size) {
        Node.fromString(inputLines[i]).let { node ->
            nodes[node.name] = node
        }
    }

    val stepAmmounts = nodes.values
        .filter { it.name.endsWith("A") }
        .map { PathGoer(nodes, instructions, it) }
        .map { it.findStepsToEnd() }


    stepAmmounts.forEach {
        println(it)
    }

    println()
    println(lcm(stepAmmounts.map { it.toBigInteger() }))



}

fun gcd(a: BigInteger, b: BigInteger): BigInteger {
    if (b == BigInteger.ZERO) {
        return a
    } else {
        return gcd(b, a % b)
    }
}

fun lcm(a: BigInteger, b: BigInteger): BigInteger {
    return a * (b / gcd(a, b))
}

fun lcm(numbers: List<BigInteger>): BigInteger {
    var result = numbers[0]
    for (i in 1 until numbers.size) {
        result = lcm(result, numbers[i])
    }
    return result
}

class PathGoer(
    val nodes: Map<String, Node>,
    val instructions: List<String>,
    startingNode: Node
) {

    private var instruction = 0
    var currentNode: Node
        private set

    init {
        currentNode = startingNode
    }

    fun findStepsToEnd(): Int {
        var steps = 0
        while (!currentNode.name.endsWith("Z")) {
            step()
            steps++
        }
        return steps
    }
    fun step() {
        val ins = instructions[instruction++]
        if (instruction >= instructions.size) {
            instruction = 0
        }
        when (ins) {
            "R" -> {
                currentNode = nodes[currentNode.right]!!
            }

            "L" -> {
                currentNode = nodes[currentNode.left]!!
            }
        }
    }
}

class Part02 {
}