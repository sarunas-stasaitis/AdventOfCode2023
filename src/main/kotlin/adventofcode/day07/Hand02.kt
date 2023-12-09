package adventofcode.day07

import com.google.common.collect.ComparisonChain
import com.google.common.collect.HashMultiset

class Hand02(
    handDefinition: String
) : Comparable<Hand02> {

    val cards = handDefinition.toCharArray().map { Card02.fromString(it.toString()) }

    val strength = run {
        val cardCounts = HashMultiset.create(cards)
        if (!cardCounts.contains(Card02.J)) {
            getBasePower(cards)
        } else if (cardCounts.entrySet().size == 1 || cardCounts.entrySet().size == 2) {
            7
        } else if (cardCounts.entrySet().size == 5) {
            2
        } else if (cardCounts.entrySet().size == 4) {
            4
        } else if (cardCounts.entrySet().size == 3) {
            if (cardCounts.entrySet().maxOf { it.count } == 3 || cardCounts.count(Card02.J) == 2) {
                6
            } else {
                5
            }
        } else {
            throw IllegalStateException("Unexpected hand: $handDefinition")
        }
    }

    private fun getBasePower(cards: List<Card02>): Int {
        val cardCounts = HashMultiset.create(cards)
        val countSet = cardCounts.entrySet().map { it.count }
        return if (countSet.contains(5)) {
            7
        } else if (countSet.contains(4)) {
            6
        } else if (countSet.contains(3) && countSet.contains(2)) {
            5
        } else if (countSet.contains(3)) {
            4
        } else if (countSet.contains(2) && countSet.size == 3) {
            3
        } else if (countSet.contains(2)) {
            2
        } else {
            1
        }
    }

    override fun compareTo(other: Hand02): Int {
        return ComparisonChain.start()
            .compare(strength, other.strength)
            .compare(cards[0], other.cards[0])
            .compare(cards[1], other.cards[1])
            .compare(cards[2], other.cards[2])
            .compare(cards[3], other.cards[3])
            .compare(cards[4], other.cards[4])
            .result()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Hand02

        if (cards != other.cards) return false
        if (strength != other.strength) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cards.hashCode()
        result = 31 * result + strength
        return result
    }

    override fun toString(): String {
        return cards.joinToString("")
    }


}