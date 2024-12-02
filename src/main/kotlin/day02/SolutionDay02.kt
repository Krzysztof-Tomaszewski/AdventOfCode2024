package day02

import utils.getListOfIntListsFromFile
import kotlin.math.abs

fun main() {
    val list = getListOfIntListsFromFile("/day02/input.txt")

    val result1 = safeReportsCount(list)
    val result2 = safeReportsCountWithProblemDampener(list)
    println("Part 1 result: $result1")
    println("Part 2 result: $result2")
}

enum class OrderType(val orderViolation: (Int, Int) -> Boolean) {
    ASC({ a, b -> a > b }),
    DESC({ a, b -> a < b })
}

fun stepViolation(element1: Int, element2: Int): Boolean {
    val minStep = 1
    val maxStep = 3
    return abs(element1 - element2) !in minStep..maxStep
}

fun getListWithoutElementIndex(list: List<Int>, indexToFilterOut: Int) =
    list.filterIndexed { index, _ -> index != indexToFilterOut }

fun safeReportsCountWithProblemDampener(list: List<List<Int>>): Int {
    fun isListAlmostSorted(list: List<Int>, orderType: OrderType, violationOccurredBefore: Boolean = false): Boolean {
        for (i in 0..<list.size - 1) {
            if (orderType.orderViolation(list[i], list[i + 1]) || stepViolation(list[i], list[i + 1])) {
                if (violationOccurredBefore) return false

                return isListAlmostSorted(getListWithoutElementIndex(list, i), orderType, true)
                        || isListAlmostSorted(getListWithoutElementIndex(list, i + 1), orderType, true)
            }
        }
        return true
    }

    return list
        .map { levels -> isListAlmostSorted(levels, OrderType.ASC) || isListAlmostSorted(levels, OrderType.DESC) }
        .count { isSafe -> isSafe }
}

fun safeReportsCount(list: List<List<Int>>): Int {
    fun isListSorted(list: List<Int>, orderType: OrderType): Boolean {
        for (i in 0..<list.size - 1) {
            if (orderType.orderViolation(list[i], list[i + 1]) || stepViolation(list[i], list[i + 1])) return false
        }
        return true
    }

    return list
        .map { levels -> isListSorted(levels, OrderType.ASC) || isListSorted(levels, OrderType.DESC) }
        .count { isSafe -> isSafe }
}