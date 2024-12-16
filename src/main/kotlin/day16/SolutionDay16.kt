package day16

import utils.getListOfCharListsFromFile
import java.util.*

enum class Direction(val symbol: Char, val coords: Pair<Int, Int>, val next: Char, val prev: Char) {
    UP('^', -1 to 0, '>', '<'),
    RIGHT('>', 0 to 1, 'v', '^'),
    DOWN('v', 1 to 0, '<', '>'),
    LEFT('<', 0 to -1, '^', 'v');
}

fun fromSymbol(symbol: Char): Direction {
    return when (symbol) {
        Direction.UP.symbol -> Direction.UP
        Direction.RIGHT.symbol -> Direction.RIGHT
        Direction.DOWN.symbol -> Direction.DOWN
        Direction.LEFT.symbol -> Direction.LEFT
        else -> throw IllegalArgumentException("Illegal direction!")
    }
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
    val maze = getListOfCharListsFromFile("/day16/input.txt")

    val result1 = findPathWithFewestTurns(maze)
    val result2 = findPathsWithFewestTurns(maze).flatten().toSet().size

    println("Part 1 result: $result1")//Part 1 result: 115500
    println("Part 2 result: $result2")//Part 2 result: 679
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

fun findPathWithFewestTurns(maze: MutableList<MutableList<Char>>): Int {

    val rows = maze.size
    val cols = maze[0].size

    val visited = Array(rows) { Array(cols) { IntArray(4) { Int.MAX_VALUE } } }
    val queue = PriorityQueue<Node>()
    val startingPosition = findStartingPosition(maze)

    queue.add(Node(startingPosition, Direction.RIGHT, 0))

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
            val newScore = current.score + if (direction != current.direction) 1001 else 1

            if (maze[newPosition] != '#' && newScore < visited[newPosition][direction.ordinal]) {
                queue.add(Node(newPosition, direction, newScore))
            }
        }
    }

    return -1
}

fun findPathsWithFewestTurns(maze: MutableList<MutableList<Char>>): List<List<Pair<Int, Int>>> {
    val rows = maze.size
    val cols = maze[0].size

    val visited = Array(rows) { Array(cols) { mutableMapOf<Direction, Int>() } }
    val queue = PriorityQueue<Node>()
    val results = mutableListOf<List<Pair<Int, Int>>>()
    val startingPosition = findStartingPosition(maze)

    queue.add(Node(startingPosition, Direction.RIGHT, 0, mutableListOf(startingPosition)))

    var minScore = Int.MAX_VALUE
    while (queue.isNotEmpty()) {
        val current = queue.poll()

        if (maze[current.position] == 'E') {
            if (current.score <= minScore) {
                minScore = current.score
                results.add(current.path)
            }
            continue
        }

        val currentVisited = visited[current.position]
        if (currentVisited[current.direction]?.let { it < current.score } == true) {
            continue
        }
        currentVisited[current.direction] = current.score

        for (direction in Direction.entries) {
            val newPosition = current.position + direction.coords
            val newScore = current.score + if (direction != current.direction) 1001 else 1

            if (maze[newPosition] != '#' && newScore <= minScore) {
                val newPath = current.path.toMutableList()
                newPath.add(newPosition)
                queue.add(Node(newPosition, direction, newScore, newPath))
            }
        }
    }

    return results.distinctBy { it.toSet() }
}