package adventofcode.day08

import adventofcode.Input

fun main() {
    val inputLines = Input(Part02::class.java).toList()

    val instructions = inputLines[0].toCharArray().map { it.toString() }

    val nodes = mutableMapOf<String, Node>()

    for (i in 2 until inputLines.size) {
        Node.fromString(inputLines[i]).let { node ->
            nodes[node.name] = node
        }
    }

    var steps = 0
    var instruction = 0
    var currentNode = nodes["AAA"]!!
    while (true) {
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
        steps++
        println("Step $steps: ${currentNode.name}")
        if (currentNode.name == "ZZZ") {
            break
        }
    }

    println()
    println("Steps: $steps")


}

class Part01 {
}