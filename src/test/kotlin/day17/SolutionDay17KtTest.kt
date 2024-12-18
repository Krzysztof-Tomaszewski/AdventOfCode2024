package day17

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay17KtTest {

    @Test
    fun partOneSimpleCase() {
        val computer = Computer(729L, 0L, 0L)

        val result = computer.runProgram(listOf(0,1,5,4,3,0))

        assertEquals(listOf(4,6,3,5,6,3,5,2,1,0), result)
    }

    @Test
    fun partTwoSimpleCase() {
        val programInput = ProgramInput(2024, 0, 0, listOf(0,3,5,4,3,0))

        val result = findRegisterAValueToOutputCopyOfInputProgram(programInput)

        assertEquals(117440L, result)
    }
}