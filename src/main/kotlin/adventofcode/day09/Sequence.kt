package adventofcode.day09

import java.util.Arrays

class Sequence(private val head: Array<Long>, private val tail: Array<Long>) {

    fun previousHead(): Array<Long> {
        val previousHead = Array(head.size) { 0L }
        for (i in 1 until head.size) {
            previousHead[i] = head[i] - previousHead[i - 1]
        }
        return previousHead
    }

    fun previousValue(): Long {
        return previousHead().last()
    }

    fun nextTail(): Array<Long> {
        val nextTail = Array(tail.size) { 0L }
        for (i in 1 until tail.size) {
            nextTail[i] = nextTail[i - 1] + tail[i]
        }
        return nextTail
    }
    fun nextValue(): Long {
        return nextTail().last()
    }

    companion object {
        fun fromString(input: String): Sequence {
            val example = mutableListOf<Array<Long>>()
            var previousLevel = input.split(" ").filter { it.isNotBlank() }.map { it.toLong() }.toTypedArray()
            example.add(previousLevel)
            while (true) {
                val nextLevel = mutableListOf<Long>()
                for (i in 0 until previousLevel.size - 1) {
                    nextLevel.add(previousLevel[i + 1] - previousLevel[i])
                }
                previousLevel = nextLevel.toTypedArray()
                example.add(previousLevel)
                if (previousLevel.filter { it != 0L }.isEmpty()) {
                    break
                }
            }

            val tail = Array(example.size) { 0L }
            for (i in 0 until example.size) {
                tail[i] = example[i].last()
            }

            val head = Array(example.size) { 0L }
            for (i in 0 until example.size) {
                head[i] = example[i].first()
            }

            return Sequence(head.reversedArray(), tail.reversedArray())
        }
    }

}