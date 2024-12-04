package day04

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay04KtTest {
    @Test
    fun partOneSimpleCase() {
        val list = listOf(
            "MMMSXXMASM".toList(),
            "MSAMXMSMSA".toList(),
            "AMXSXMAAMM".toList(),
            "MSAMASMSMX".toList(),
            "XMASAMXAMM".toList(),
            "XXAMMXXAMA".toList(),
            "SMSMSASXSS".toList(),
            "SAXAMASAAA".toList(),
            "MAMMMXMMMM".toList(),
            "MXMXAXMASX".toList()
        )

        val result = countXMASOccurrences(list)

        assertEquals(18, result)
    }

    @Test
    fun partTwoSimpleCase() {
        val list = listOf(
            ".M.S......".toList(),
            "..A..MSMS.".toList(),
            ".M.S.MAA..".toList(),
            "..A.ASMSM.".toList(),
            ".M.S.M....".toList(),
            "..........".toList(),
            "S.S.S.S.S.".toList(),
            ".A.A.A.A..".toList(),
            "M.M.M.M.M.".toList(),
            "..........".toList()
        )

        val result = countCrossedMASOccurrences(list)

        assertEquals(9, result)
    }
}










