package adventofcode.day07

import adventofcode.Input
import org.jooq.lambda.Seq.seq
import java.util.TreeMap


fun main() {

    val map = TreeMap<Hand01, Int>()

    Input(Part02::class.java).forEach {
        val split = it.split(" ")
        val hand = Hand01(split[0])
        map[hand] = split[1].toInt()
    }

    map.forEach { (hand, count) ->
        println("$hand: $count")
    }

    val score = seq(map.entries)
        .zipWithIndex()
        .mapToInt {
            it.v1().value * (it.v2() + 1).toInt()
        }
        .sum()

    println()
    println(score)

}
class Part01 {
}