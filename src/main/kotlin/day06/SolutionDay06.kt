package day06

import utils.getListOfCharListsFromFile

enum class Direction(val symbol: Char, val coords: Pair<Int, Int>, val next: Char) {
    UP('^', -1 to 0, '>'),
    RIGHT('>', 0 to 1, 'v'),
    DOWN('v', 1 to 0, '<'),
    LEFT('<', 0 to -1, '^');
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

private operator fun Pair<Int, Int>.plus(pair: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first + pair.first) to (this.second + pair.second)
}

private fun findStartingPositionAndDirection(matrix: MutableList<MutableList<Char>>): Pair<Pair<Int, Int>, Direction> {
    var startingPosition: Pair<Int, Int> = -1 to -1
    var direction : Direction = Direction.UP //default
    for (i in matrix.indices) {
        for (j in matrix[0].indices) {
            if (matrix[i][j] == '^' || matrix[i][j] == '>' || matrix[i][j] == 'v' || matrix[i][j] == '<') {
                startingPosition = i to j
                direction = fromSymbol(matrix[i][j])
            }
        }
    }
    return startingPosition to direction
}

fun guardRoute(originalMatrix: MutableList<MutableList<Char>>): Pair<Boolean, MutableList<MutableList<Char>>> {
    val matrix = originalMatrix.map { it.toMutableList() }.toMutableList()
    val visitedPositions: MutableSet<Pair<Pair<Int, Int>, Direction>> = mutableSetOf()
    var (currentPosition, currentDirection) = findStartingPositionAndDirection(matrix)
    var loopDetected = false

    while (!loopDetected && currentPosition.first in matrix.indices && currentPosition.second in matrix[0].indices) {

        val nextPosition = currentPosition + currentDirection.coords
        when {
            nextPosition.first !in matrix.indices || nextPosition.second !in matrix.indices
                    || matrix[nextPosition] == '.' || matrix[nextPosition] == 'X' -> {
                if (matrix[currentPosition] != 'X') {
                    matrix[currentPosition] = 'X'
                    visitedPositions.add(currentPosition to currentDirection)
                } else {
                    if (visitedPositions.contains(currentPosition to currentDirection)) {
                        loopDetected = true
                    }
                }
                currentPosition += currentDirection.coords
            }

            matrix[nextPosition] == '#' -> {
                currentDirection = fromSymbol(currentDirection.next)
            }
        }
    }
    return loopDetected to matrix
}

fun countVisitedPositions(originalMatrix: MutableList<MutableList<Char>>): Int {
    var count = 0
    val matrixWithGuardRoute: MutableList<MutableList<Char>> = guardRoute(originalMatrix).second
    for (row in matrixWithGuardRoute.indices) {
        for (col in matrixWithGuardRoute[row].indices) {
            if (matrixWithGuardRoute[row][col] == 'X') {
                count++
            }
        }
    }
    return count
}

fun countObstructionsMakingLoops(originalMatrix: MutableList<MutableList<Char>>): Int {
    var count = 0
    val matrixWithGuardRoute: MutableList<MutableList<Char>> = guardRoute(originalMatrix).second
    for (row in matrixWithGuardRoute.indices) {
        for (col in matrixWithGuardRoute[row].indices) {
            if (matrixWithGuardRoute[row][col] == 'X') {
                val matrixToCheck = originalMatrix.map { it.toMutableList() }.toMutableList()
                matrixToCheck[row][col] = '#'
                if (guardRoute(matrixToCheck).first) count++
            }
        }
    }
    return count
}

fun main() {
    val matrix = getListOfCharListsFromFile("/day06/input.txt")

    val result1 = countVisitedPositions(matrix)
    val result2 = countObstructionsMakingLoops(matrix)
    println("Part 1 result: $result1")//Part 1 result: 5208
    println("Part 2 result: $result2")//Part 2 result: 1972
}

