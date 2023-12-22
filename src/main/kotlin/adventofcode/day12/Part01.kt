package adventofcode.day12

import adventofcode.Input
import org.jooq.lambda.Seq
import org.jooq.lambda.Seq.seq
import java.util.stream.IntStream


fun main() {

    var total = 0L
    for (line in Input(Part02::class.java)) {
        val (states, errors) = parseLine(line)
        println(line)
        val validArrangements = findPermutations(states, errors)
            .count()
        println(validArrangements)
        println()

        total += validArrangements
    }

    println(total)

}

fun findPermutations(inStates: Array<SpringState>, errors: IntArray): Seq<Array<SpringState>> {
    val changeableLocations = Seq.of(*inStates)
        .zipWithIndex()
        .filter { it.v1() == SpringState.UNKNOWN }
        .mapToInt { it.v2().toInt() }
        .toArray()

    val locationCount = changeableLocations.size
    val count = pow(2, locationCount)

    return seq(
        IntStream.range(0, count)
            .mapToObj {
                val copy = inStates.copyOf()
                for (i in 0 until locationCount) {
                    val location = changeableLocations[i]
                    val bit = it and (1 shl i)
                    val state = if (bit == 0) SpringState.OK else SpringState.BROKEN
                    copy[location] = state
                }
                copy
            }
            .filter { isValid(it, errors) }
    )
}

fun pow(base: Int, exponent: Int): Int {
    var result = 1
    for (i in 0 until exponent) {
        result *= base
    }
    return result
}

fun parseLine(line: String): Pair<Array<SpringState>, IntArray> {
    val split = line.split(" ")
    val states = parseSprings(split[0])
    val errors = parseErrors(split[1])
    return Pair(states, errors)
}

fun parseErrors(line: String): IntArray {
    return line.split(",").stream().mapToInt() { it.toInt() }.toArray()
}

fun parseSprings(line: String): Array<SpringState> {
    return line.map { SpringState.fromChar(it) }.toTypedArray()
}

fun isValid(states: Array<SpringState>, errors: IntArray): Boolean {
    data class State(val state: SpringState, val count: Int)

    val stateCounts = mutableListOf<State>()

    var currentState = states[0]
    var currentCount = 1
    for (i in 1 until states.size) {
        val state = states[i]
        if (state == currentState) {
            currentCount++
        } else {
            stateCounts.add(State(currentState, currentCount))
            currentState = state
            currentCount = 1
        }
    }
    stateCounts.add(State(currentState, currentCount))

    val toArray = seq(stateCounts)
        .filter { it.state == SpringState.BROKEN }
        .mapToInt { it.count }
        .toArray()

    return errors.contentEquals(toArray)
}

enum class SpringState {
    OK,
    BROKEN,
    UNKNOWN;

    companion object {
        fun fromChar(c: Char): SpringState {
            return when (c) {
                '.' -> OK
                '#' -> BROKEN
                else -> UNKNOWN
            }
        }

    }
}

class Part01 {
}