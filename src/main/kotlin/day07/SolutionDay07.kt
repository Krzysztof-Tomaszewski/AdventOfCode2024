package day07

import utils.getIntAndListOfIntsPairsFromFile

fun main() {
    val list = getIntAndListOfIntsPairsFromFile("/day07/input.txt")

    val result1 = sumValidTestCases(list, listOf('+', '*'))
    val result2 = sumValidTestCases(list, listOf('+', '*', '|'))
    println("Part 1 result: $result1")//Part 1 result: 1399219271639
    println("Part 2 result: $result2")//Part 2 result: 275791737999003
}

fun sumValidTestCases(list: List<Pair<Long, List<Long>>>, operators: List<Char>): Long {
    return list.filter { pair -> isTestCaseValid(pair, operators) }
        .sumOf { pair -> pair.first }
}

val generatedPermutations : MutableMap<Pair<List<Char>, Int>,List<List<Char>>> = mutableMapOf()
fun isTestCaseValid(pair: Pair<Long, List<Long>>, operators: List<Char>): Boolean {
    val expected = pair.first
    val ints = pair.second

    val permutationsOfOperators = generatedPermutations.getOrDefault(operators to (ints.size - 1), generatePermutationsWithRepetition(operators, ints.size - 1))
    generatedPermutations.putIfAbsent(operators to (ints.size - 1), permutationsOfOperators)

    permutationsOfOperators.forEach { permutation ->
        run {
            var result = ints[0]
            for (i in permutation.indices) {
                when {
                    permutation[i] == '*' -> {
                        result *= ints[i + 1]
                    }
                    permutation[i] == '+' -> {
                        result += ints[i + 1]
                    }
                    permutation[i] == '|' -> {
                        result = (result.toString() + ints[i+1].toString()).toLong()
                    }
                }

                if (result > expected) break
            }
            if (result == expected) return true
        }
    }
    return false
}

fun generatePermutationsWithRepetition(operators: List<Char>, permutationsSize: Int): List<List<Char>> {
    val result = mutableListOf<List<Char>>()

    fun backtrack(currentPermutation: MutableList<Char>) {
        if (currentPermutation.size == permutationsSize) {
            result.add(currentPermutation.toList())
            return
        }

        for (number in operators) {
            currentPermutation.add(number)
            backtrack(currentPermutation)
            currentPermutation.removeAt(currentPermutation.lastIndex)
        }
    }

    backtrack(mutableListOf())
    return result
}

