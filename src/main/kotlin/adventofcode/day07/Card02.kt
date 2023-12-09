package adventofcode.day07

enum class Card02 {

    J,
    N2 {
        override fun toString(): String {
            return "2"
        }
    },
    N3 {
        override fun toString(): String {
            return "3"
        }
    },
    N4 {
        override fun toString(): String {
            return "4"
        }
    },
    N5 {
        override fun toString(): String {
            return "5"
        }
    },
    N6 {
        override fun toString(): String {
            return "6"
        }
    },
    N7 {
        override fun toString(): String {
            return "7"
        }
    },
    N8 {
        override fun toString(): String {
            return "8"
        }
    },
    N9 {
        override fun toString(): String {
            return "9"
        }
    },
    T,
    Q,
    K,
    A;

    companion object {
        fun fromString(card: String): Card02 {
            return when (card) {
                "A" -> A
                "K" -> K
                "Q" -> Q
                "J" -> J
                "T" -> T
                "9" -> N9
                "8" -> N8
                "7" -> N7
                "6" -> N6
                "5" -> N5
                "4" -> N4
                "3" -> N3
                "2" -> N2
                else -> throw IllegalArgumentException("Invalid card: $card")
            }
        }
    }
}