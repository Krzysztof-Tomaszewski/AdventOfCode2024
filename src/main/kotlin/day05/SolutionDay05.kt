package day05

import utils.getIntPairsAndIntListsFromFile

fun main() {
    val input = getIntPairsAndIntListsFromFile("/day05/input.txt")

    val result1 = sumMiddlesOfCorrectUpdates(input.first, input.second)
    val result2 = sumMiddlesOfCorrectedUpdates(input.first, input.second)
    println("Part 1 result: $result1")//Part 1 result: 5964
    println("Part 2 result: $result2")//Part 2 result: 4719
}

fun sumMiddlesOfCorrectUpdates(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
    return updates.filter { list -> !violatesTheRules(list, rules) }
        .sumOf { list -> list[list.size / 2] }
}

fun sumMiddlesOfCorrectedUpdates(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {

    fun sortByRules(ints: List<Int>): List<Int> {
        return getOrderedListFromRules(rules.filter { (a,b) -> ints.contains(a) && ints.contains(b) })
// if ints could have additional values, not covered by rules:
//        val orderedListFromRules = getOrderedListFromRules(rules.filter { (a,b) -> ints.contains(a) && ints.contains(b) })
//        val (intsToOrder, rest) = ints.partition { x -> orderedListFromRules.contains(x) }
//        val orderedInts = orderedListFromRules.filter { x -> intsToOrder.contains(x) }
//
//        return orderedInts + rest
    }


    return updates.filter { list -> violatesTheRules(list, rules) }
        .map { list -> sortByRules(list) }
        .sumOf { list -> list[list.size / 2] }
}

fun violatesTheRules(update: List<Int>, rules: List<Pair<Int, Int>> ): Boolean {
    val updateMap = update.mapIndexed { index, i -> i to index }.toMap()

    for (rule in rules) {
        val el1 = updateMap[rule.first]
        val el2 = updateMap[rule.second]

        if (el1 != null && el2 != null && el1 > el2) {
            return true
        }
    }
    return false
}

fun getOrderedListFromRules(rules: List<Pair<Int, Int>>): List<Int> {
    val dependencies = mutableMapOf<Int, MutableSet<Int>>()
    val dependencyCountMap = mutableMapOf<Int, Int>()
    for (rule in rules) {
        dependencyCountMap.putIfAbsent(rule.first, 0)
        dependencies.putIfAbsent(rule.first, mutableSetOf())
        dependencyCountMap.putIfAbsent(rule.second, 0)
        dependencies.putIfAbsent(rule.second, mutableSetOf())

        dependencies[rule.first]!!.add(rule.second)
        dependencyCountMap[rule.second] = dependencyCountMap[rule.second]!!.inc()
    }
    val orderedList = mutableListOf<Int>()
    while (dependencyCountMap.isNotEmpty()) {
        val toRemove = mutableSetOf<Int>()
        for (entry in dependencyCountMap) {
            if (entry.value == 0) {
                orderedList.add(entry.key)
                dependencies[entry.key]!!.forEach { a -> dependencyCountMap[a] = dependencyCountMap[a]!!.dec() }
                toRemove.add(entry.key)
            }
        }
        check(toRemove.isNotEmpty()) { "Cycle in rules!" }
        for (key in toRemove) {
            dependencyCountMap.remove(key)
        }

    }
    return orderedList
}