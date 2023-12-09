package adventofcode.day06

import adventofcode.Input

fun main() {
    val input = Input(Part02::class.java).toList()

    val times = input[0].split(" ").filter { it.isNotBlank() }.map { it.toLong() }
    val distances = input[1].split(" ").filter { it.isNotBlank() }.map { it.toLong() }

    val wayToBeat = Array(times.size) { 0 }

    for (i in times.indices) {
        for (t in 0..times[i]) {
            if (distances[i] < distance(t, times[i])) {
                wayToBeat[i]++;
            }
        }
    }

    wayToBeat.forEach { println(it) }
    println()
    println(wayToBeat.reduce(Int::times))

}

fun distance(holdTime: Long, totalTime: Long) : Long {
    val runTime = totalTime - holdTime
    return runTime * holdTime
}

class Part01 {
}