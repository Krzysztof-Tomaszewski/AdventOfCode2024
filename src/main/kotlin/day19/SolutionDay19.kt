package day19

import utils.getStringListFromFile

fun main() {
    val availableTowels = getStringListFromFile("/day19/input1.txt").map { str -> str.split(", ") }.flatten()
    val designs = getStringListFromFile("/day19/input2.txt")

    val result1 = howManyDesignsCanBeMade(availableTowels, designs)
    val result2 = sumWaysOfCreatingDesigns(availableTowels, designs)

    println("Part 1 result: $result1")//Part 1 result: 209
    println("Part 2 result: $result2")//Part 2 result: 777669668613191
}

fun howManyDesignsCanBeMade(availableTowels: List<String>, designs: List<String>): Int {
    return designs.count { design -> countWaysToCompose(design, availableTowels.toSet()) > 0L }
}

fun sumWaysOfCreatingDesigns(availableTowels: List<String>, designs: List<String>): Long {
    return designs.sumOf { design -> countWaysToCompose(design, availableTowels.toSet()) }
}

fun countWaysToCompose(word: String, sequences: Set<String>): Long {
    val n = word.length
    val dp = LongArray(n + 1) { 0 }
    dp[0] = 1

    for (i in 1..n) {
        for (seq in sequences) {
            if (i >= seq.length && word.substring(i - seq.length, i) == seq) {
                dp[i] += dp[i - seq.length]
            }
        }
    }

    return dp[n]
}