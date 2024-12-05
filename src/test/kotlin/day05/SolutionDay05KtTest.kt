package day05

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class SolutionDay05KtTest {

    @Test
    fun getOrderedListFromRules() {
        val rules = listOf(
            1 to 2,
            3 to 2,
            3 to 4,
            2 to 4,
            4 to 5
        )

        val prototype = getOrderedListFromRules(rules)

        assertEquals(listOf(1,3,2,4,5), prototype)
    }
}