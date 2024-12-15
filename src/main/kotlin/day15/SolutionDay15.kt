package day15

import utils.getListOfCharListsFromFile
import utils.getStringFromFile

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

private fun findStartingPosition(matrix: MutableList<MutableList<Char>>): Pair<Int, Int> {
    var startingPosition: Pair<Int, Int> = -1 to -1
    for (row in matrix.indices) {
        for (col in matrix[0].indices) {
            if (matrix[row][col] == '@') {
                startingPosition = row to col
                matrix[startingPosition] = '.'
                return startingPosition
            }
        }
    }
    return startingPosition
}

fun robotRoute(originalMatrix: MutableList<MutableList<Char>>, route: String): MutableList<MutableList<Char>> {
    val matrix = originalMatrix.map { it.toMutableList() }.toMutableList()
    var currentPosition = findStartingPosition(matrix)
    route.toList().map { direction -> fromSymbol(direction) }
        .forEach { currentDirection ->
            run {
                val nextPosition = currentPosition + currentDirection.coords
                when {
                    matrix[nextPosition] == '.' -> {
                        currentPosition += currentDirection.coords
                    }

                    matrix[nextPosition] == 'O' -> {
                        if (tryToPush(matrix, nextPosition, currentDirection)) {
                            push(matrix, nextPosition, currentDirection)
                            currentPosition += currentDirection.coords
                        }
                    }

                    matrix[nextPosition] == '[' -> {
                        if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
                            if (canBePushed(matrix, nextPosition, currentDirection) && canBePushed(
                                    matrix,
                                    nextPosition + (0 to 1),
                                    currentDirection
                                ) && tryToPush(matrix, nextPosition, currentDirection) && tryToPush(
                                    matrix,
                                    nextPosition + (0 to 1),
                                    currentDirection
                                )
                            ) {
                                push(matrix, nextPosition, currentDirection)
                                push(matrix, nextPosition+ (0 to 1), currentDirection)
                                currentPosition += currentDirection.coords
                            }
                        } else {
                            if (tryToPush(matrix, nextPosition, currentDirection)) {
                                push(matrix, nextPosition, currentDirection)
                                currentPosition += currentDirection.coords
                            }
                        }
                    }

                    matrix[nextPosition] == ']' -> {
                        if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
                            if (canBePushed(matrix, nextPosition, currentDirection) && canBePushed(
                                    matrix,
                                    nextPosition + (0 to -1),
                                    currentDirection
                                ) && tryToPush(matrix, nextPosition, currentDirection) && tryToPush(
                                    matrix,
                                    nextPosition + (0 to -1),
                                    currentDirection
                                )
                            ) {
                                push(matrix, nextPosition, currentDirection)
                                push(matrix, nextPosition+ (0 to -1), currentDirection)
                                currentPosition += currentDirection.coords
                            }
                        } else {
                            if (tryToPush(matrix, nextPosition, currentDirection)) {
                                push(matrix, nextPosition, currentDirection)
                                currentPosition += currentDirection.coords
                            }
                        }
                    }
                }
            }
        }
    return matrix
}

private fun push(
    matrix: MutableList<MutableList<Char>>,
    nextPosition: Pair<Int, Int>,
    currentDirection: Direction
) {
    matrix[nextPosition + currentDirection.coords] = matrix[nextPosition]
    matrix[nextPosition] = '.'
}

fun canBePushed(
    matrix: MutableList<MutableList<Char>>,
    currentPosition: Pair<Int, Int>,
    currentDirection: Direction
): Boolean {
    val nextPosition = currentPosition + currentDirection.coords
    return when {
        matrix[nextPosition] == '.' -> {
            true
        }

        matrix[nextPosition] == 'O' -> {
            canBePushed(matrix, nextPosition, currentDirection)
        }

        matrix[nextPosition] == '[' -> {
            if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
                canBePushed(matrix, nextPosition, currentDirection) && canBePushed(matrix, nextPosition + (0 to 1), currentDirection)
            } else {
                canBePushed(matrix, nextPosition, currentDirection)
            }
        }

        matrix[nextPosition] == ']' -> {
            if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
                canBePushed(matrix, nextPosition, currentDirection)
                        && canBePushed(matrix, nextPosition + (0 to -1), currentDirection)
            } else {
                canBePushed(matrix, nextPosition, currentDirection)
            }
        }

        else -> false
    }
}

fun tryToPush(
    matrix: MutableList<MutableList<Char>>,
    currentPosition: Pair<Int, Int>,
    currentDirection: Direction
): Boolean {
    val nextPosition = currentPosition + currentDirection.coords
    return when {
        matrix[nextPosition] == '.' -> {
            true
        }

        matrix[nextPosition] == 'O' -> {
            handleHorizontalPush(matrix, nextPosition, currentDirection)
        }

        matrix[nextPosition] == '[' -> {
            if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
                handleVerticalPush(matrix, nextPosition, currentDirection, 0 to 1)
            } else {
                handleHorizontalPush(matrix, nextPosition, currentDirection)
            }
        }

        matrix[nextPosition] == ']' -> {
            if (currentDirection == Direction.UP || currentDirection == Direction.DOWN) {
                handleVerticalPush(matrix, nextPosition, currentDirection, 0 to -1)
            } else {
                handleHorizontalPush(matrix, nextPosition, currentDirection)
            }
        }

        else -> false
    }
}

private fun handleVerticalPush(
    matrix: MutableList<MutableList<Char>>,
    nextPosition: Pair<Int, Int>,
    currentDirection: Direction, secondPosOffset: Pair<Int, Int>
) = if (tryToPush(matrix, nextPosition, currentDirection) && tryToPush(
        matrix,
        nextPosition + secondPosOffset,
        currentDirection
    )
) {
    push(matrix, nextPosition, currentDirection)
    push(matrix, nextPosition + secondPosOffset, currentDirection)
    true
} else {
    false
}

private fun handleHorizontalPush(
    matrix: MutableList<MutableList<Char>>,
    nextPosition: Pair<Int, Int>,
    currentDirection: Direction
) = if (tryToPush(matrix, nextPosition, currentDirection)) {
    push(matrix, nextPosition, currentDirection)
    true
} else {
    false
}


fun main() {
    val matrix = getListOfCharListsFromFile("/day15/input1.txt")
    val route = getStringFromFile("/day15/input2.txt")
    val widerMap = getWiderMap(matrix)
    val result1 = calculateSumOfBoxesGPSCoords(matrix, route)
    val result2 = calculateSumOfBoxesGPSCoords(widerMap, route)
    println("Part 1 result: $result1")//Part 1 result: 1526018
    println("Part 2 result: $result2")//Part 2 result: 1550677
}

fun getWiderMap(originalMap: MutableList<MutableList<Char>>): MutableList<MutableList<Char>> {
    val widerMap: MutableList<MutableList<Char>> = mutableListOf()
    for (row in originalMap.indices) {
        val widerRow: MutableList<Char> = mutableListOf()
        for (col in originalMap[0].indices) {
            when (originalMap[row][col]) {
                '.' -> {
                    widerRow.add('.')
                    widerRow.add('.')
                }

                '#' -> {
                    widerRow.add('#')
                    widerRow.add('#')
                }

                'O' -> {
                    widerRow.add('[')
                    widerRow.add(']')
                }

                '@' -> {
                    widerRow.add('@')
                    widerRow.add('.')
                }
            }
        }
        widerMap.add(widerRow)
    }
    return widerMap
}

fun calculateSumOfBoxesGPSCoords(matrix: MutableList<MutableList<Char>>, route: String): Long {
    val mapAfterRobotRoute = robotRoute(matrix, route)
    var sum = 0L
    for (row in mapAfterRobotRoute.indices) {
        for (col in mapAfterRobotRoute[0].indices) {
            if (mapAfterRobotRoute[row][col] == 'O' || mapAfterRobotRoute[row][col] == '[') {
                sum += (100 * row + col)
            }
        }
    }
    return sum
}

