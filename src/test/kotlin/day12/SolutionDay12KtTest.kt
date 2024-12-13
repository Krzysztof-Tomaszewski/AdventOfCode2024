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
    fun partTwoSimpleCase1() {
        val map = mutableListOf(
            "AABB".toMutableList(),
            "AABB".toMutableList(),
            "CCDD".toMutableList(),
            "CCDD".toMutableList()
        )

        val result = calculatePriceOfFencingAllRegionsWithDiscount(map)

        assertEquals(64, result)
    }

    @Test
    fun partTwoSimpleCas2() {
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

    @Test
    fun partTwoSimpleCas6() {
        val map = mutableListOf(
            "CCAAA".toMutableList(),
            "CCAAA".toMutableList(),
            "AABBA".toMutableList(),
            "AAAAA".toMutableList()
        )

        val result = calculatePriceOfFencingAllRegionsWithDiscount(map)

        assertEquals(164, result)
    }
}