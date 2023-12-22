package adventofcode.day12

import adventofcode.Input
import org.jooq.lambda.Seq
import org.jooq.lambda.Seq.seq
import java.util.stream.IntStream


fun main() {

    var total = 0L
    for (line in Input(Part02::class.java)) {
        val (states, errors) = parseLine2(line)
        println(line)
        val validArrangements = findPermutations(states, errors)
            .count()
        println(validArrangements)
        println()

        total += validArrangements
    }

    println(total)

}

fun parseLine2(line: String): Pair<Array<SpringState>, IntArray> {
    val split = line.split(" ")
    val states = parseSprings(unfold(split[0], "?"))
    val errors = parseErrors(unfold(split[1], ","))
    return Pair(states, errors)
}

fun countValidPermutations(states: Array<SpringState>, errors: IntArray): Long {
    while (true) {

    }
}

fun unfold(input: String, separator: String): String {
    return Seq.generate(input).limit(5).toString(separator)
}

class Part02 {
}