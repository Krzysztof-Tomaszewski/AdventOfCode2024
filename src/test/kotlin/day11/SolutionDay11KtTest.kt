package day11

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay11KtTest {
    @Test
    fun partOneSimpleCase1() {
        val list = listOf<Long>(125L, 17L)

        val result = countStonesAfterApplyingRules(list, 6)

        assertEquals(22, result)
    }

    @Test
    fun partOneSimpleCase2() {
        val list = listOf<Long>(125L, 17L)

        val result = countStonesAfterApplyingRules(list, 25)

        assertEquals(55312, result)
    }
}