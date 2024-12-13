package day13

import utils.getListOfTripleIntPairs
import java.math.BigDecimal

fun main() {
    val machines = getListOfTripleIntPairs("/day13/input.txt")

    val result1 = winAllPossiblePrizes(machines, 0)
    val result2 = winAllPossiblePrizes(machines, 10000000000000)
    println("Part 1 result: $result1")//Part 1 result: 36954
    println("Part 2 result: $result2")//Part 2 result: 79352015273424
}

fun winAllPossiblePrizes(machines: List<Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>>>, offset: Long): BigDecimal {
    return machines.sumOf { machine -> winPrize(machine, offset) }

}

fun winPrize(machine: Triple<Pair<Int, Int>, Pair<Int, Int>, Pair<Int, Int>>, offset: Long): BigDecimal {
    val xA = BigDecimal(machine.first.first)
    val yA = BigDecimal(machine.first.second)
    val xB = BigDecimal(machine.second.first)
    val yB = BigDecimal(machine.second.second)
    val xP = BigDecimal(machine.third.first) + BigDecimal(offset)
    val yP = BigDecimal(machine.third.second) + BigDecimal(offset)

    val butA = (xP*yB - yP*xB) / (xA*yB - yA*xB)
    val butB = (xA*yP - yA*xP) / (xA*yB - yA*xB)
    return if ((xA * butA + xB * butB to yA * butA + yB * butB) == xP to yP) {
        butA * BigDecimal(3) + butB
    } else {
        BigDecimal.ZERO
    }
}
