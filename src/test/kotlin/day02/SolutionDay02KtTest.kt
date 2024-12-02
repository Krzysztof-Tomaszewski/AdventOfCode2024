package day02

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay02KtTest {

    @Test
    fun partOneSimpleCase() {
        val list = listOf(
            listOf(7, 6, 4, 2, 1),
            listOf(1, 2, 7, 8, 9),
            listOf(9, 7, 6, 2, 1),
            listOf(1, 3, 2, 4, 5),
            listOf(8, 6, 4, 4, 1),
            listOf(1, 3, 6, 7, 9)
        )

        val safeReportsCount = safeReportsCount(list)

        assertEquals(2, safeReportsCount)
    }

    @Test
    fun partTwoSimpleCase() {
        val list = listOf(
            listOf(7, 6, 4, 2, 1),
            listOf(1, 2, 7, 8, 9),
            listOf(9, 7, 6, 2, 1),
            listOf(1, 3, 2, 4, 5),
            listOf(8, 6, 4, 4, 1),
            listOf(1, 3, 6, 7, 9)
        )

        val safeReportsCount = safeReportsCountWithProblemDampener(list)

        assertEquals(4, safeReportsCount)
    }
}