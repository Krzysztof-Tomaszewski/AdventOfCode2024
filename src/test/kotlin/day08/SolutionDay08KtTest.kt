package day08

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay08KtTest {
    @Test
    fun partOneSimpleCase() {
        val list = mutableListOf(
            "............".toMutableList(),
            "........0...".toMutableList(),
            ".....0......".toMutableList(),
            ".......0....".toMutableList(),
            "....0.......".toMutableList(),
            "......A.....".toMutableList(),
            "............".toMutableList(),
            "............".toMutableList(),
            "........A...".toMutableList(),
            ".........A..".toMutableList(),
            "............".toMutableList(),
            "............".toMutableList()
        )

        val result = countAntinodes(list)

        assertEquals(14, result)
    }

    @Test
    fun partTwoSimpleCase() {
        val list = mutableListOf(
            "............".toMutableList(),
            "........0...".toMutableList(),
            ".....0......".toMutableList(),
            ".......0....".toMutableList(),
            "....0.......".toMutableList(),
            "......A.....".toMutableList(),
            "............".toMutableList(),
            "............".toMutableList(),
            "........A...".toMutableList(),
            ".........A..".toMutableList(),
            "............".toMutableList(),
            "............".toMutableList()
        )

        val result = countAntinodesWithResonant(list)

        assertEquals(34, result)
    }

    @Test
    fun partTwoSimpleCase2() {
        val list = mutableListOf(
            "T.........".toMutableList(),
            "...T......".toMutableList(),
            ".T........".toMutableList(),
            "..........".toMutableList(),
            "..........".toMutableList(),
            "..........".toMutableList(),
            "..........".toMutableList(),
            "..........".toMutableList(),
            "..........".toMutableList(),
            "..........".toMutableList(),
        )

        val result = countAntinodesWithResonant(list)

        assertEquals(9, result)
    }
}
