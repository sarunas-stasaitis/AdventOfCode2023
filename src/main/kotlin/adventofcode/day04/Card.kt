package adventofcode.day04

class Card(
    val index: Int,
    val winningNumbers: List<Int>,
    val selectedNumbers: List<Int>
) {

    constructor(index: Int, game: String) : this(
        index,
        game.split(":")[1].split("|")[0].split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() },
        game.split(":")[1].split("|")[1].split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() }
    )

    var count = 1
    val wins: Int = selectedNumbers.count {
        winningNumbers.contains(it)
    }


    override fun toString(): String {
        return "Card(index = ${index}, count=${count}, wins=${wins})"
    }

}