package adventofcode.day09

import adventofcode.Input

fun main() {
    val input = Input(Part02::class.java).map { Sequence.fromString(it) }

    val nextValues = input.map { it.nextValue() }
    println(nextValues.sum())
}

class Part01 {
}