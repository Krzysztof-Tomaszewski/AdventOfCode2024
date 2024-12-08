package day08

import utils.getListOfCharListsFromFile

private operator fun Pair<Int, Int>.plus(pair: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first + pair.first) to (this.second + pair.second)
}

private operator fun Pair<Int, Int>.times(pair: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first * pair.first) to (this.second * pair.second)
}

private operator fun Pair<Int, Int>.minus(pair: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first - pair.first) to (this.second - pair.second)
}


fun countAntinodes(map: MutableList<MutableList<Char>>): Int {
    val antennaOccurrences: MutableMap<Char, MutableSet<Pair<Int, Int>>> = mutableMapOf()
    for (row in map.indices) {
        for (col in map[row].indices) {
            if (map[row][col] != '.') {
                antennaOccurrences.getOrPut(map[row][col]) { mutableSetOf() }.add(row to col)
            }
        }
    }
    return antennaOccurrences.values
        .flatMap { signals -> getAntinodesPerSignalType(signals, map) }
        .toSet()
        .count()

}

fun getAntinodesPerSignalType(
    signals: MutableSet<Pair<Int, Int>>,
    map: MutableList<MutableList<Char>>
): Set<Pair<Int, Int>> {
    val antinodes: MutableSet<Pair<Int, Int>> = mutableSetOf()
    for (signal in signals) {
        for (oppositeSignal in signals) {
            if (signal != oppositeSignal) {
                val antinode = signal + (signal - oppositeSignal)
                if (antinode.first in map.indices && antinode.second in map[0].indices) {
                    antinodes.add(antinode)
                }
            }
        }
    }
    return antinodes
}

fun countAntinodesWithResonant(map: MutableList<MutableList<Char>>): Int {
    val antennaOccurrences: MutableMap<Char, MutableSet<Pair<Int, Int>>> = mutableMapOf()
    for (row in map.indices) {
        for (col in map[row].indices) {
            if (map[row][col] != '.') {
                antennaOccurrences.getOrPut(map[row][col]) { mutableSetOf() }.add(row to col)
            }
        }
    }
    return antennaOccurrences.values
        .flatMap { signals -> getAntinodesPerSignalTypeWithResonant(signals, map) }
        .toSet()
        .count()

}

fun getAntinodesPerSignalTypeWithResonant(
    signals: MutableSet<Pair<Int, Int>>,
    map: MutableList<MutableList<Char>>
): Set<Pair<Int, Int>> {
    val antinodes: MutableSet<Pair<Int, Int>> = mutableSetOf()
    for (signal in signals) {
        for (oppositeSignal in signals) {
            if (signal != oppositeSignal) {
                antinodes.add(signal)
                val diff = signal - oppositeSignal
                var antinode = signal + diff
                while (antinode.first in map.indices && antinode.second in map[0].indices) {
                    antinodes.add(antinode)
                    antinode += diff
                }
            }
        }
    }
    return antinodes
}

fun main() {
    val map = getListOfCharListsFromFile("/day08/input.txt")

    val result1 = countAntinodes(map)
    val result2 = countAntinodesWithResonant(map)
    println("Part 1 result: $result1")//Part 1 result: 329
    println("Part 2 result: $result2")//Part 2 result: 1190
}

