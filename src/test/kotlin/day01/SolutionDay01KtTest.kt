package day01

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay01KtTest {
    @Test
    fun partOneSimpleCase() {
        val list1: List<Int> = listOf(3, 4, 2, 1, 3, 3);
        val list2: List<Int> = listOf(4, 3, 5, 3, 9, 3);

        val distanceBetweenLists = distanceBetweenLists(list1, list2)

        assertEquals(11, distanceBetweenLists)
    }

    @Test
    fun partTwoSimpleCase() {
        val list1: List<Int> = listOf(3, 4, 2, 1, 3, 3);
        val list2: List<Int> = listOf(4, 3, 5, 3, 9, 3);

        val similarityScore = similarityScore(list1, list2)

        assertEquals(31, similarityScore)
    }
}