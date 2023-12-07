package adventofcode.day05

import adventofcode.Input
import com.google.common.collect.Range

fun main() {

    val seeds = mutableListOf<Long>()
    val converters = mutableListOf<(Long) -> Long>()

    val currentConverterLines = mutableListOf<String>()

    for (line in Input(Part2::class.java)) {
        if (seeds.isEmpty()) {
            line.split(": ")[1].split(" ").filter { it.isNotBlank() }.map { it.toLong() }.forEach { seeds.add(it) }
        } else if (line.isBlank() && currentConverterLines.isNotEmpty()) {
            converters.add(createConverter(ArrayList(currentConverterLines)))
            currentConverterLines.clear()
        } else if (!line.endsWith(":") && line.isNotBlank()) {
            currentConverterLines.add(line)
        }
    }
    converters.add(createConverter(ArrayList(currentConverterLines)))

    val locations = mutableListOf<Long>()

    for (seed in seeds) {
        var current = seed
        for (converter in converters) {
            print("$current -> ")
            current = converter(current)
        }
        println("$current")
        locations.add(current)
    }

    println("Min location: ${locations.min()}")
}

fun createConverter(lines: List<String>): (Long) -> Long {
    val ranges = LinkedHashMap<Range<Long>, Long>()
    lines.forEach() {
        val numbers = it.split(" ").filter { it.isNotBlank() }.map { it.toLong() }
        val inputRange = Range.closedOpen(numbers[1], numbers[1] + numbers[2])
        val outputOrigin = numbers[0]
        ranges[inputRange] = outputOrigin
    }
    return { input ->
        val e = ranges.entries.find { it.key.contains(input) }
        if (e != null) {
            val range = e.key
            val origin = e.value
            origin + input - range.lowerEndpoint()
        } else {
            input
        }
    }
}

class Part1 {
}