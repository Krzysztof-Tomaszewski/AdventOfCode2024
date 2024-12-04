package day04

import utils.getListOfCharListsFromFile

fun main() {
    val list = getListOfCharListsFromFile("/day04/input.txt")

    val result1 = countXMASOccurrences(list)
    val result2 = countCrossedMASOccurrences(list)
    println("Part 1 result: $result1")//Part 1 result: 2571
    println("Part 2 result: $result2")//Part 2 result:
}

fun countXMASOccurrences(matrix: List<List<Char>>): Int {
    val queue: ArrayDeque<Triple<Int, Int, Pair<Int, Int>?>> = ArrayDeque()
    var count = 0
    for (i in matrix.indices) {
        for (j in matrix[0].indices) {
            if (matrix[j][i] == 'X') {
                queue.addLast(Triple(j, i, null))
            }
        }
    }

    fun findCharsInNeighbours(char: Char, coords: Pair<Int, Int>, specifiedDirection: Pair<Int, Int>?) {
        val (x, y) = coords

        val directions = listOf(
            0 to 1, 0 to -1, 1 to 1, 1 to -1,
            1 to 0, -1 to 0, -1 to -1, -1 to 1
        )

        fun checkDirection(direction: Pair<Int, Int>) {
            val j = x + direction.first
            val i = y + direction.second

            if (i >= 0 && j >= 0 && i < matrix.size && j < matrix[0].size
                && matrix[j][i] == char
            ) {
                queue.addLast(Triple(j, i, direction))
            }
        }

        if (specifiedDirection != null) {
            checkDirection(specifiedDirection)
        } else {
            for (direction in directions) {
                checkDirection(direction)
            }
        }
    }

    while (queue.isNotEmpty()) {
        val (x, y, direction) = queue.removeFirst()
        when (matrix[x][y]) {
            'X' -> findCharsInNeighbours('M', x to y, null)
            'M' -> findCharsInNeighbours('A', x to y, direction)
            'A' -> findCharsInNeighbours('S', x to y, direction)
            'S' -> count++
        }
    }
    return count
}

fun countCrossedMASOccurrences(matrix: List<List<Char>>): Int {
    val queue: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
    var count = 0
    for (i in matrix.indices) {
        for (j in matrix[0].indices) {
            if (matrix[j][i] == 'A') {
                queue.addLast(j to i)
            }
        }
    }

    while (queue.isNotEmpty()) {
        val (x, y) = queue.removeFirst()

        if (x != 0 && y != 0 && x != matrix[0].size - 1 && y != matrix.size - 1 &&
            ((matrix[x - 1][y - 1] == 'S' && matrix[x + 1][y + 1] == 'M') || (matrix[x - 1][y - 1] == 'M' && matrix[x + 1][y + 1] == 'S'))
            &&
            ((matrix[x + 1][y - 1] == 'S' && matrix[x - 1][y + 1] == 'M') || (matrix[x + 1][y - 1] == 'M' && matrix[x - 1][y + 1] == 'S'))
        ) {
            count++;
        }
    }
    return count
}

