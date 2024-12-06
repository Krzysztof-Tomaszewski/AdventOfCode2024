package day06

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay06KtTest {

    @Test
    fun partOneSimpleCase() {
        val matrix = mutableListOf(
            "....#.....".toMutableList(),
            ".........#".toMutableList(),
            "..........".toMutableList(),
            "..#.......".toMutableList(),
            ".......#..".toMutableList(),
            "..........".toMutableList(),
            ".#..^.....".toMutableList(),
            "........#.".toMutableList(),
            "#.........".toMutableList(),
            "......#...".toMutableList()
        )

        val result = countVisitedPositions(matrix)

        matrix.forEach{ l -> println(l)}
        assertEquals(41, result)
    }

    @Test
    fun shouldNotDetectLoop() {
        val matrix = mutableListOf(
            "....#.....".toMutableList(),
            ".........#".toMutableList(),
            "..........".toMutableList(),
            "..#.......".toMutableList(),
            ".......#..".toMutableList(),
            "..........".toMutableList(),
            ".#..^.....".toMutableList(),
            "........#.".toMutableList(),
            "#.........".toMutableList(),
            "......#...".toMutableList()
        )

        val result = guardRoute(matrix).first

        assertEquals(false, result)
    }

    @Test
    fun shouldDetectLoop() {
        val matrix = mutableListOf(
            "....#.....".toMutableList(),
            ".........#".toMutableList(),
            "..........".toMutableList(),
            "..##......".toMutableList(),
            ".......##.".toMutableList(),
            "..........".toMutableList(),
            ".#..^.....".toMutableList(),
            "........#.".toMutableList(),
            "#.........".toMutableList(),
            "......#...".toMutableList()
        )

        val result = guardRoute(matrix).first

        assertEquals(true, result)
    }

    @Test
    fun partTwoSimpleCase() {
        val matrix = mutableListOf(
            "....#.....".toMutableList(),
            ".........#".toMutableList(),
            "..........".toMutableList(),
            "..#.......".toMutableList(),
            ".......#..".toMutableList(),
            "..........".toMutableList(),
            ".#..^.....".toMutableList(),
            "........#.".toMutableList(),
            "#.........".toMutableList(),
            "......#...".toMutableList()
        )

        val result = countObstructionsMakingLoops(matrix)

        assertEquals(6, result)
    }
}