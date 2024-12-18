package day17

import utils.getStringListFromFile


data class ProgramInput(val regA: Int, val regB: Int, val regC: Int, val program: List<Int>) {}

fun main() {
    val programInput: ProgramInput = getProgramInput(getStringListFromFile("/day17/input.txt"))

    val computer = Computer(programInput.regA.toLong(), programInput.regB.toLong(), programInput.regC.toLong())
    val result1 = computer.runProgram(programInput.program)
    val result2 = findRegisterAValueToOutputCopyOfInputProgram(programInput)

    println("Part 1 result: $result1")//Part 1 result: 7,6,1,5,3,1,4,2,6
    println("Part 2 result: $result2")//Part 2 result: 164541017976509
}

fun findRegisterAValueToOutputCopyOfInputProgram(programInput: ProgramInput): Long {
    val computer = Computer(programInput.regA.toLong(), programInput.regB.toLong(), programInput.regC.toLong())

    fun resetAndReRun(a: Long) : List<Int>{
        computer.output = ""
        computer.registryA = a
        computer.registryB = programInput.regB.toLong()
        computer.registryC = programInput.regC.toLong()
        return computer.runProgram(programInput.program)
    }

    var count = 3
    var targetOutput = programInput.program.takeLast(count)
    var a = 0L

    while (true) {
        val output = resetAndReRun(a)
        if (output == targetOutput) {
            if (count == programInput.program.size) {
                break
            }
            count++
            targetOutput = programInput.program.takeLast(count)
            a *= 8
        } else {
            a++
        }
    }
    return a
}

fun getProgramInput(fileInput: List<String>): ProgramInput {
    var a = 0
    var b = 0
    var c = 0
    var program : List<Int> = mutableListOf()
    fileInput.forEach { line ->
        if (line.startsWith("Register A: "))
            a = line.substringAfterLast(" ").toInt()
        else if (line.startsWith("Register B: "))
            b = line.substringAfterLast(" ").toInt()
        else if (line.startsWith("Register C: "))
            c = line.substringAfterLast(" ").toInt()
        else if (line.startsWith("Program: "))
            program = line.substringAfterLast(" ").split(",").map { it -> it.toInt() }
    }
    return ProgramInput(a, b, c, program)
}
