package day13

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class SolutionDay13KtTest {

    @Test
    fun partOneSimpleCase() {
        val machines = listOf(
            Triple(
                94 to 34,
                22 to 67,
                8400 to 5400
            ),
            Triple(
                26 to 66,
                67 to 21,
                12748 to 12176
            ),
            Triple(
                17 to 86,
                84 to 37,
                7870 to 6450
            ),
            Triple(
                69 to 23,
                27 to 71,
                18641 to 10279
            )
        )

        val result = winAllPossiblePrizes(machines, 0)

        assertEquals(BigDecimal(480), result)
    }

    @Test
    fun partTwoSimpleCase() {
        val machines = listOf(
            Triple(
                94 to 34,
                22 to 67,
                8400 to 5400
            ),
            Triple(
                26 to 66,
                67 to 21,
                12748 to 12176
            ),
            Triple(
                17 to 86,
                84 to 37,
                7870 to 6450
            ),
            Triple(
                69 to 23,
                27 to 71,
                18641 to 10279
            )
        )

        val result = winAllPossiblePrizes(machines, 10000000000000)

        assertEquals(BigDecimal(875318608908), result)
    }
}