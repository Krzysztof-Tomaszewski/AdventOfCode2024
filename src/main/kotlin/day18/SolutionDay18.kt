package day18

import utils.getIntsPairsFromFile
import java.util.*

enum class Direction(val symbol: Char, val coords: Pair<Int, Int>, val next: Char, val prev: Char) {
    UP('^', -1 to 0, '>', '<'),
    RIGHT('>', 0 to 1, 'v', '^'),
    DOWN('v', 1 to 0, '<', '>'),
    LEFT('<', 0 to -1, '^', 'v');
}

private operator fun <E> MutableList<MutableList<E>>.set(pair: Pair<Int, Int>, value: E): E {
    this[pair.first][pair.second] = value
    return value
}

private operator fun <E> List<List<E>>.get(pair: Pair<Int, Int>): E {
    return this[pair.first][pair.second]
}

private operator fun <T> Array<Array<T>>.get(pair: Pair<Int, Int>): T {
    return this[pair.first][pair.second]
}

private operator fun Pair<Int, Int>.plus(pair: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first + pair.first) to (this.second + pair.second)
}

fun main() {
    val fallenBytes = getIntsPairsFromFile("/day18/input.txt")
    val maze = buildMazeFromFallenBytes(fallenBytes.take(1024), 71, 71)

    val result1 = findShortestPath(maze)
    val result2 = findFirstFailingByte(maze, fallenBytes, 1024)

    println("Part 1 result: $result1")//Part 1 result: 348
    println("Part 2 result: $result2")//Part 2 result: (54, 44)
}

fun findFirstFailingByte(maze: MutableList<MutableList<Char>>, fallenBytes: List<Pair<Int, Int>>, start: Int): Pair<Int, Int> {

    for (i in start..fallenBytes.size) {
        val (col, row) = fallenBytes[i]
        maze[row][col] = '#'

        val path = findShortestPath(maze)
        if (path == -1) return col to row
    }
    return -1 to -1
}

fun buildMazeFromFallenBytes(
    fallenBytes: List<Pair<Int, Int>>,
    width: Int,
    height: Int
): MutableList<MutableList<Char>> {
    val start = 0 to 0
    val end = height - 1 to width - 1
    val maze: MutableList<MutableList<Char>> = mutableListOf()

    for (row in 0..<height) {
        val fullRow = mutableListOf<Char>()
        for (col in 0..<width) {
            fullRow.add(
                when {
                    row to col == start -> 'S'
                    row to col == end -> 'E'
                    fallenBytes.contains(col to row) -> '#'
                    else -> '.'
                }
            )
        }
        maze.add(fullRow)
    }
    return maze
}

private fun findStartingPosition(matrix: MutableList<MutableList<Char>>): Pair<Int, Int> {
    var startingPosition: Pair<Int, Int> = -1 to -1
    for (row in matrix.indices) {
        for (col in matrix[0].indices) {
            if (matrix[row][col] == 'S') {
                startingPosition = row to col
                return startingPosition
            }
        }
    }
    return startingPosition
}

data class Node(
    val position: Pair<Int, Int>, val direction: Direction, val score: Int,
    val path: List<Pair<Int, Int>> = listOf()
) : Comparable<Node> {
    override fun compareTo(other: Node): Int {
        return this.score - other.score
    }
}

fun findShortestPath(maze: MutableList<MutableList<Char>>): Int {

    val rows = maze.size
    val cols = maze[0].size

    val visited = Array(rows) { Array(cols) { IntArray(4) { Int.MAX_VALUE } } }
    val queue = PriorityQueue<Node>()
    val startingPosition = findStartingPosition(maze)

    queue.add(Node(startingPosition, Direction.RIGHT, 0))
    queue.add(Node(startingPosition, Direction.DOWN, 0))

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        if (maze[current.position] == 'E') {
            return current.score
        }

        if (current.score >= visited[current.position][current.direction.ordinal]) {
            continue
        }

        visited[current.position][current.direction.ordinal] = current.score

        for (direction in Direction.entries) {
            val newPosition = current.position + direction.coords
            val newScore = current.score + 1

            if (newPosition.first in maze.indices && newPosition.second in maze[0].indices && maze[newPosition] != '#'
                && newScore < visited[newPosition][direction.ordinal]
            ) {
                queue.add(Node(newPosition, direction, newScore))
            }
        }
    }

    return -1
}