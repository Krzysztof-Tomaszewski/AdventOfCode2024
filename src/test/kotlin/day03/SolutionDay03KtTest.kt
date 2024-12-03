package day03

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SolutionDay03KtTest {

    @Test
    fun partOneSimpleCase() {
        val list = listOf(
            "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))\n",
            "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))\n"
        )

        val result = calculateCorruptedMemory(list)

        assertEquals(322, result)
    }

    @Test
    fun partTwoSimpleCase() {
        val list = listOf(
            "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))",
            "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"
        )

        val result = calculateCorruptedMemoryWithConditionals(list)

        assertEquals(96, result)
    }
}