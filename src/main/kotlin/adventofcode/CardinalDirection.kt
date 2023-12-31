package adventofcode

enum class CardinalDirection4 {

    N(0, -1),
    E(1, 0),
    S(0, 1),
    W(-1, 0);

    val dx: Int
    val dy: Int

    constructor(dx: Int, dy: Int) {
        this.dx = dx
        this.dy = dy
    }

}