package adventofcode.day06

import adventofcode.Input

fun main() {
    val input = Input(Part02::class.java).toList()

    val times = Array(1) { input[0].split(" ").filter { it.isNotBlank() }.joinToString("").toLong() }
    val distances = Array(1) { input[1].split(" ").filter { it.isNotBlank() }.joinToString("").toLong() }

    val wayToBeat = Array(times.size) { 0L }

    for (i in times.indices) {
        for (t in 0..times[i]) {
            if (distances[i] < distance(t, times[i])) {
                wayToBeat[i]++;
            }
        }
    }

    wayToBeat.forEach { println(it) }
    println()
    println(wayToBeat.reduce(Long::times))

}

class Part02 {
}