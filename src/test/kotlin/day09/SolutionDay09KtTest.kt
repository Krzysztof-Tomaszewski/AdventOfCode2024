package day09

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay09KtTest {

    @Test
    fun partOneSimpleCase1() {
        val str = "2333133121414131402"

        assertEquals(1928, checksumAfterCompactingByBlock( str))
    }

    @Test
    fun partTwoSimpleCase1() {
        val str = "2333133121414131402"

        assertEquals(2858, checksumAfterCompactingByFile( str))
    }

    @Test
    fun partTwoSimpleCase2() {
        val str = "23331331214141314022113"

        assertEquals(3317, checksumAfterCompactingByFile( str))
    }
}