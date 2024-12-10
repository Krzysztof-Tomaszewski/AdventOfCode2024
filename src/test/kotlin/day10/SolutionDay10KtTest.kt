package day10

import day04.countXMASOccurrences
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay10KtTest {

    @Test
    fun partOneSimpleCase() {
        val list = listOf(
            "89010123".toList(),
            "78121874".toList(),
            "87430965".toList(),
            "96549874".toList(),
            "45678903".toList(),
            "32019012".toList(),
            "01329801".toList(),
            "10456732".toList()
        )

        val result = sumTrailheadsScore(list, mutableSetOf())

        assertEquals(36, result)
    }

    @Test
    fun partTwoSimpleCase() {
        val list = listOf(
            "89010123".toList(),
            "78121874".toList(),
            "87430965".toList(),
            "96549874".toList(),
            "45678903".toList(),
            "32019012".toList(),
            "01329801".toList(),
            "10456732".toList()
        )

        val result = sumTrailheadsScore(list, mutableListOf())

        assertEquals(81, result)
    }
}