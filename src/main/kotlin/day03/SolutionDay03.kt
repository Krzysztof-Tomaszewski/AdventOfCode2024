package day03

import utils.getStringListFromFile

fun main() {
    val list = getStringListFromFile("/day03/input.txt")

    val result1 = calculateCorruptedMemory(list)
    val result2 = calculateCorruptedMemoryWithConditionals(list)
    println("Part 1 result: $result1")//Part 1 result: 175015740
    println("Part 2 result: $result2")//Part 2 result: 112272912
}

fun calculateCorruptedMemory(list: List<String>): Int {
    val regex = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
    return list.map { str ->
        regex.findAll(str).map { matchResult ->
            val x = matchResult.groupValues[1].toInt()
            val y = matchResult.groupValues[2].toInt()
            x to y
        }.toList()
            .map { (x, y) -> x * y }
            .reduce { acc, i -> acc + i }
    }.reduce { acc, i -> acc + i }
}

fun calculateCorruptedMemoryWithConditionals(list: List<String>): Int {
    val instructionsRegex = Regex("""do\(\)|don't\(\)|mul\(\d{1,3},\d{1,3}\)""")
    val eachMulRegex = Regex("""\((\d{1,3}),(\d{1,3})\)""")

    val joinedMemory = list.joinToString()
    val instructions = instructionsRegex.findAll(joinedMemory).map { it.value }.toList()

    var enabled = true
    var sum = 0
    for (instruction in instructions) {
        when (instruction) {
            "do()" -> enabled = true
            "don't()" -> enabled = false
            else -> {
                if (enabled) {
                    val matchResult = eachMulRegex.find(instruction)
                    val x = matchResult!!.groupValues[1].toInt()
                    val y = matchResult.groupValues[2].toInt()
                    sum += (x * y)
                }
            }
        }
    }

    return sum
}
