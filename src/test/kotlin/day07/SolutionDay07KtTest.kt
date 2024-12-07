package day07

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionDay07KtTest {

    @Test
    fun shouldGenerateAllPermutationsWithRepetitions() {

        val result = generatePermutationsWithRepetition(listOf('+', '*'), 2)

        assertEquals(
            result, listOf(
                listOf('+', '+'),
                listOf('+', '*'),
                listOf('*', '+'),
                listOf('*', '*')
            )
        )
    }

    @Test
    fun partOneSimpleCase() {
        val list = listOf(
            190L to listOf(10L, 19L),
            3267L to listOf(81L, 40L, 27L),
            83L to listOf(17L, 5L),
            156L to listOf(15L, 6L),
            7290L to listOf(6L, 8L, 6L, 15L),
            161011L to listOf(16L, 10L, 13L),
            192L to listOf(17L, 8L, 14L),
            21037L to listOf(9L, 7L, 18L, 13L),
            292L to listOf(11L, 6L, 16L, 20L)
        )

        val result = sumValidTestCases(list, listOf('+', '*'))

        assertEquals(3749, result)
    }

    @Test
    fun partTwoSimpleCase() {
        val list = listOf(
            190L to listOf(10L, 19L),
            3267L to listOf(81L, 40L, 27L),
            83L to listOf(17L, 5L),
            156L to listOf(15L, 6L),
            7290L to listOf(6L, 8L, 6L, 15L),
            161011L to listOf(16L, 10L, 13L),
            192L to listOf(17L, 8L, 14L),
            21037L to listOf(9L, 7L, 18L, 13L),
            292L to listOf(11L, 6L, 16L, 20L)
        )

        val result = sumValidTestCases(list, listOf('+', '*', '|'))

        assertEquals(11387, result)
    }
}