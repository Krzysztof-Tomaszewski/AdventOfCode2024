package day18

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay18KtTest {

    private val fallenBytes = listOf(
        (5 to 4),
        (4 to 2),
        (4 to 5),
        (3 to 0),
        (2 to 1),
        (6 to 3),
        (2 to 4),
        (1 to 5),
        (0 to 6),
        (3 to 3),
        (2 to 6),
        (5 to 1),
        (1 to 2),
        (5 to 5),
        (2 to 5),
        (6 to 5),
        (1 to 4),
        (0 to 4),
        (6 to 4),
        (1 to 1),
        (6 to 1),
        (1 to 0),
        (0 to 5),
        (1 to 6),
        (2 to 0)
    )

    @Test
    fun partOneSimpleCase() {
        val maze = buildMazeFromFallenBytes(fallenBytes.take(12), 7, 7)

        val result = findShortestPath(maze)

        assertEquals(22, result)
    }

    @Test
    fun partTwoSimpleCase() {
        val maze = mutableListOf(
            "S..#...".toMutableList(),
            "..#..#.".toMutableList(),
            "....#..".toMutableList(),
            "...#..#".toMutableList(),
            "..#..#.".toMutableList(),
            ".#..#..".toMutableList(),
            "#.#...E".toMutableList()
        )

        val result = findFirstFailingByte(maze, fallenBytes, 12)

        assertEquals(6 to 1, result)
    }

}

