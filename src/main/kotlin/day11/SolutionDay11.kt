package day11

import utils.getLongListFromFile

fun main() {
    val stones = getLongListFromFile("/day11/input.txt")

    val result1 = countStonesAfterApplyingRules(stones, 25)
    val result2 = stones.sumOf { stone -> countStonesAfterApplyingRules(listOf(stone), 75) }
    println("Part 1 result: $result1")//Part 1 result: 185894
    println("Part 2 result: $result2")//Part 2 result: 221632504974231
}

fun applyRulesToStone(stone: Long): List<Long> {
    return when {
        stone == 0L -> listOf(1L)
        stone.toString().length % 2 == 0 -> {
            val half: Int = stone.toString().length / 2
            listOf(
                stone.toString().substring(0, half).toLong(),
                stone.toString().substring(half).toLong()
            )
        }
        else -> listOf(stone * 2024L)
    }
}

fun countStonesAfterApplyingRules(originalStones: List<Long>, iterations: Int): Long {
    var counts = originalStones.groupingBy { it }.eachCount()
        .toMutableMap().mapValues {  entry -> entry.value.toLong() }

    for (iteration in 1..iterations) {
        val newCounts = mutableMapOf<Long, Long>()

        counts.forEach { (number, count) ->
            val newNumbers = applyRulesToStone(number)
            newNumbers.forEach { newNumber ->
                newCounts[newNumber] = newCounts.getOrDefault(newNumber, 0L) + count
            }
        }
        counts = newCounts
    }

    return counts.values.sum()
}