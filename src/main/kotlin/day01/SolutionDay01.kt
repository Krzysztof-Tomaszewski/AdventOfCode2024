package day01

import utils.getTwoIntListsFromFile
import kotlin.math.abs

fun main() {
    val (list1, list2) = getTwoIntListsFromFile("/day01/input1.txt")

    val result1 = distanceBetweenLists(list1, list2)
    val result2 = similarityScore(list1, list2)
    println("Part 1 result: $result1")
    println("Part 2 result: $result2")
}

fun distanceBetweenLists (list1 : List<Int>, list2 : List<Int>) : Int {
    val sortedList1 = list1.sorted()
    val sortedList2 = list2.sorted()

    return sortedList1.zip(sortedList2)
        .map { (e1, e2) -> abs(e1 - e2) }
        .reduce {acc, i -> acc + i }
}

fun similarityScore (list1 : List<Int>, list2 : List<Int>) : Int {
    val freq: MutableMap<Int, Int> = HashMap()
    list2.forEach { e -> freq[e] = 1 + freq.getOrDefault(e, 0) }

    return list1.map { e -> e * freq.getOrDefault(e, 0) }
        .reduce { acc, i -> acc + i }
}
