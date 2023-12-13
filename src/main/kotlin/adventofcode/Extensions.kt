package adventofcode

import org.jooq.lambda.tuple.Tuple2

class Extensions {

    companion object {
        operator fun <T> Tuple2<T, *>.component1(): T {
            return this.v1()
        }

        operator fun <T> Tuple2<*, T>.component2(): T {
            return this.v2()
        }

    }

}