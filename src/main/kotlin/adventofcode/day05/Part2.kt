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

    var minLocation = Long.MAX_VALUE

    var i = 0
    while (i < seeds.size) {
        var origin = seeds[i]
        var range = seeds[i+1]

        for (seed in origin..(origin+range)) {
            var current = seed
            for (converter in converters) {
                current = converter(current)
            }

            minLocation = Math.min(minLocation, current)
        }

        i+=2
    }

    println("Min location: ${minLocation}")
}

class Part2 {
}