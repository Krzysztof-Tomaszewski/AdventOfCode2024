package day16

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay16KtTest {

    @Test
    fun partOneSimpleCase() {
        val maze = mutableListOf(
            "###############".toMutableList(),
            "#.......#....E#".toMutableList(),
            "#.#.###.#.###.#".toMutableList(),
            "#.....#.#...#.#".toMutableList(),
            "#.###.#####.#.#".toMutableList(),
            "#.#.#.......#.#".toMutableList(),
            "#.#.#####.###.#".toMutableList(),
            "#...........#.#".toMutableList(),
            "###.#.#####.#.#".toMutableList(),
            "#...#.....#.#.#".toMutableList(),
            "#.#.#.###.#.#.#".toMutableList(),
            "#.....#...#.#.#".toMutableList(),
            "#.###.#.#.#.#.#".toMutableList(),
            "#S..#.....#...#".toMutableList(),
            "###############".toMutableList()
        )

        val result = findPathWithFewestTurns(maze)

        assertEquals(7036, result)
    }

    @Test
    fun partTwoSimpleCase() {
        val maze = mutableListOf(
            "###############".toMutableList(),
            "#.......#....E#".toMutableList(),
            "#.#.###.#.###.#".toMutableList(),
            "#.....#.#...#.#".toMutableList(),
            "#.###.#####.#.#".toMutableList(),
            "#.#.#.......#.#".toMutableList(),
            "#.#.#####.###.#".toMutableList(),
            "#...........#.#".toMutableList(),
            "###.#.#####.#.#".toMutableList(),
            "#...#.....#.#.#".toMutableList(),
            "#.#.#.###.#.#.#".toMutableList(),
            "#.....#...#.#.#".toMutableList(),
            "#.###.#.#.#.#.#".toMutableList(),
            "#S..#.....#...#".toMutableList(),
            "###############".toMutableList()
        )

        val result = findPathsWithFewestTurns(maze).flatten().toSet().size

        assertEquals(45, result)
    }
}