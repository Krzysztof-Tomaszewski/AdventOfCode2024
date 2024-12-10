package day10

import utils.getListOfCharListsFromFile

fun main() {
    val hikingMap = getListOfCharListsFromFile("/day10/input.txt")

    val result1 = sumTrailheadsScore(hikingMap, mutableSetOf())
    val result2 = sumTrailheadsScore(hikingMap, mutableListOf())
    println("Part 1 result: $result1")//Part 1 result: 538
    println("Part 2 result: $result2")//Part 2 result: 1110
}

fun sumTrailheadsScore(
    matrix: List<List<Char>>,
    trailheadsCollection: MutableCollection<Triple<Int, Int, Pair<Int, Int>>>
): Int {
    val queue: ArrayDeque<Triple<Int, Int, Pair<Int, Int>>> = ArrayDeque()
    val foundTrailheads: MutableCollection<Triple<Int, Int, Pair<Int, Int>>> = trailheadsCollection

    addStartingPointsToQueue(matrix, queue)

    fun findCharsInNeighbours(char: Char, coords: Pair<Int, Int>, startingPoint: Pair<Int, Int>) {
        val (row, col) = coords

        val directions = listOf(
            0 to 1, 0 to -1, 1 to 0, -1 to 0
        )

        fun checkDirection(direction: Pair<Int, Int>) {
            val neighbourRow = row + direction.first
            val neighbourCol = col + direction.second

            if (neighbourRow in matrix.indices && neighbourCol in matrix[0].indices
                && matrix[neighbourRow][neighbourCol] == char
            ) {
                queue.addLast(Triple(neighbourRow, neighbourCol, startingPoint))
            }
        }

        for (direction in directions) {
            checkDirection(direction)
        }
    }

    while (queue.isNotEmpty()) {
        val (row, col, startingPoint) = queue.removeFirst()
        when (matrix[row][col]) {
            '0' -> findCharsInNeighbours('1', row to col, startingPoint)
            '1' -> findCharsInNeighbours('2', row to col, startingPoint)
            '2' -> findCharsInNeighbours('3', row to col, startingPoint)
            '3' -> findCharsInNeighbours('4', row to col, startingPoint)
            '4' -> findCharsInNeighbours('5', row to col, startingPoint)
            '5' -> findCharsInNeighbours('6', row to col, startingPoint)
            '6' -> findCharsInNeighbours('7', row to col, startingPoint)
            '7' -> findCharsInNeighbours('8', row to col, startingPoint)
            '8' -> findCharsInNeighbours('9', row to col, startingPoint)

            '9' -> foundTrailheads.add(Triple(row, col, startingPoint))
        }
    }
    return foundTrailheads.size
}

private fun addStartingPointsToQueue(
    matrix: List<List<Char>>,
    queue: ArrayDeque<Triple<Int, Int, Pair<Int, Int>>>
) {
    for (row in matrix.indices) {
        for (col in matrix[0].indices) {
            if (matrix[row][col] == '0') {
                queue.addLast(Triple(row, col, row to col))
            }
        }
    }
}

