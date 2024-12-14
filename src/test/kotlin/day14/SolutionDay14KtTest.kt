package day14

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay14KtTest {

    @Test
    fun partOneSimpleCase() {
        val list = listOf(
            ((0 to 4) to (3 to -3)),
            ((6 to 3) to (-1 to -3)),
            ((10 to 3) to (-1 to 2)),
            ((2 to 0) to (2 to -1)),
            ((0 to 0) to (1 to 3)),
            ((3 to 0) to (-2 to -2)),
            ((7 to 6) to (-1 to -3)),
            ((3 to 0) to (-1 to -2)),
            ((9 to 3) to (2 to 3)),
            ((7 to 3) to (-1 to 2)),
            ((2 to 4) to (2 to -3)),
            ((9 to 5) to (-3 to -3)),
        )

        val result = calculateSafetyFactor(list, 100, 11, 7)

        assertEquals(12L, result)

    }
}