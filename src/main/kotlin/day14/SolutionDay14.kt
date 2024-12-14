package day14

import utils.getListOfPairIntPairs



fun main() {
    val robots = getListOfPairIntPairs("/day14/input.txt")
    val width = 101
    val height = 103

    val result1 = calculateSafetyFactor(robots, 100, width, height)
    val result2 = findSecondWithPossibleChristmasTree(robots, 10000, width, height)
    println("Part 1 result: $result1")//Part 1 result: 217132650
    println("Part 2 result: $result2")//Part 2 result: 6516
}

fun calculateSafetyFactor(robots: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>,
                          seconds: Int,
                          width: Int, height: Int): Long {
    val robotsPositionsAfterSimulation: List<Pair<Int, Int>> = calculateRobotsPositionsAfterSimulation(robots, seconds, width, height)

    return robotsPositionsAfterSimulation
        .map { robotPosition -> getQuarter(robotPosition, width, height) }
        .groupingBy { it }.eachCount()
        .filterKeys { it != 0 }
        .values
        .fold(1L) { acc, count -> acc * count.toLong() }

}

fun calculateRobotsPositionsAfterSimulation(
    robots: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>,
    seconds: Int,
    width: Int,
    height: Int
): List<Pair<Int, Int>> {
    return robots.map { robot ->
        var robotNewX = (robot.first.first + robot.second.first * seconds) % width
        var robotNewY = (robot.first.second + robot.second.second * seconds) % height

        if (robotNewX < 0) robotNewX += width
        if (robotNewY < 0) robotNewY += height

        robotNewX to robotNewY
    }
}

fun getQuarter(robotPosition: Pair<Int, Int>,width: Int, height: Int): Int {
    val halfW = width / 2
    val halfH = height / 2

    val ranges = mapOf(
        1 to (0..<halfW to 0..<halfH),
        2 to (halfW + 1..<width to 0..<halfH),
        3 to (0..<halfW to halfH + 1..<height),
        4 to (halfW + 1..<width to halfH + 1..<height)
    )

    if (robotPosition.first == halfW || robotPosition.second == halfH) {
        return 0
    }

    for (range in ranges) {
        if (robotPosition.first in range.value.first && robotPosition.second in range.value.second) {
            return range.key
        }
    }
    return 0
}

fun findSecondWithPossibleChristmasTree(
    originalRobots: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>,
    seconds: Int,
    width: Int,
    height: Int
): Int {
    var robots = originalRobots.toMutableList()
    for (i in 0..<seconds) {
        val tmpRobots: MutableList<Pair<Pair<Int, Int>, Pair<Int, Int>>> = mutableListOf()
        for (robot in robots) {
            val (robotNewX, robotNewY) = getNextRobotPosition(robot, width, height)
            tmpRobots.add((robotNewX to robotNewY) to robot.second)
        }
        robots = tmpRobots

        if (hasLine(robots.map { list -> list.first })) {
            return i + 1
        }
    }
    return -1
}

private fun getNextRobotPosition(
    robot: Pair<Pair<Int, Int>, Pair<Int, Int>>,
    width: Int,
    height: Int
): Pair<Int, Int> {
    var robotNewX = robot.first.first + robot.second.first
    var robotNewY = robot.first.second + robot.second.second

    if (robotNewX < 0) {
        robotNewX += width
    }
    if (robotNewY < 0) {
        robotNewY += height
    }
    if (robotNewX >= width) {
        robotNewX -= width
    }
    if (robotNewY >= height) {
        robotNewY -= height
    }
    return Pair(robotNewX, robotNewY)
}

fun hasLine(points: List<Pair<Int, Int>>): Boolean {
    for (point in points) {
        var (x,y) = point
        var found = false
        for (i in 0..<30) {
            y += 1
            if (!points.contains(x to y)) {
                break
            }
            if(i == 29) found = true
        }
        if (found) {
            return true
        }
    }
    return false
}

