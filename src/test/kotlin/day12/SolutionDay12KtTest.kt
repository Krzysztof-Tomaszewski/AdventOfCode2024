package day12

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay12KtTest {
    @Test
    fun partOneSimpleCase() {
        val map = mutableListOf(
            "AAA".toMutableList()
        )

        val result = calculatePriceOfFencingAllRegions(map)

        assertEquals(24, result)
    }

    @Test
    fun partTwoSimpleCase() {
        val map = mutableListOf(
            "AAAA".toMutableList(),
            "AABB".toMutableList()
        )

        val result = calculatePriceOfFencingAllRegionsWithDiscount(map)

        assertEquals(44, result)
    }

    @Test
    fun partTwoSimpleCas2e() {
        val map = mutableListOf(
            "AAAA".toMutableList(),
            "BBCD".toMutableList(),
            "BBCC".toMutableList(),
            "EEEC".toMutableList()
        )

        val result = calculatePriceOfFencingAllRegionsWithDiscount(map)

        assertEquals(80, result)
    }

    @Test
    fun partTwoSimpleCas3() {
        val map = mutableListOf(
            "EEEEE".toMutableList(),
            "EXXXX".toMutableList(),
            "EEEEE".toMutableList(),
            "EXXXX".toMutableList(),
            "EEEEE".toMutableList()
        )

        val result = calculatePriceOfFencingAllRegionsWithDiscount(map)

        assertEquals(236, result)
    }

    @Test
    fun partTwoSimpleCas4() {
        val map = mutableListOf(
            "AAAAAA".toMutableList(),
            "AAABBA".toMutableList(),
            "AAABBA".toMutableList(),
            "ABBAAA".toMutableList(),
            "ABBAAA".toMutableList(),
            "AAAAAA".toMutableList()
        )

        val result = calculatePriceOfFencingAllRegionsWithDiscount(map)

        assertEquals(368, result)
    }

    @Test
    fun partTwoSimpleCas5() {
        val map = mutableListOf(
            "RRRRIICCFF".toMutableList(),
            "RRRRIICCCF".toMutableList(),
            "VVRRRCCFFF".toMutableList(),
            "VVRCCCJFFF".toMutableList(),
            "VVVVCJJCFE".toMutableList(),
            "VVIVCCJJEE".toMutableList(),
            "VVIIICJJEE".toMutableList(),
            "MIIIIIJJEE".toMutableList(),
            "MIIISIJEEE".toMutableList(),
            "MMMISSJEEE".toMutableList()
        )

        val result = calculatePriceOfFencingAllRegionsWithDiscount(map)

        assertEquals(1206, result)
    }
}