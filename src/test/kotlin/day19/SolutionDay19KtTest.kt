package day19

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SolutionDay19KtTest {

    private val availableTowels = listOf("r", "wr", "b", "g", "bwu", "rb", "gb", "br")

    private val designs = listOf(
        "brwrr",
        "bggr",
        "gbbr",
        "rrbgbr",
        "ubwu",
        "bwurrg",
        "brgr",
        "bbrgwb"
    )

    @Test
    fun partOneSimpleCase() {
        val result = howManyDesignsCanBeMade(availableTowels, designs);

        assertEquals(6, result)
    }

    @Test
    fun partTwoSimpleCase() {
        val result = sumWaysOfCreatingDesigns(availableTowels, designs);

        assertEquals(16, result)
    }
}