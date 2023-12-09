package adventofcode.day08

data class Node(val name: String, val left: String, val right: String) {
    companion object {
        fun fromString(input: String): Node {
            val split = input.split(" = ")
            val directions = split[1].replace("(", "").replace(")", "").split(", ")
            return Node(split[0], directions[0], directions[1])
        }
    }
}