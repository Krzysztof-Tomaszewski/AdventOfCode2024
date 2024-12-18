package day17

import kotlin.math.pow

class Computer (
    var registryA : Long,
    var registryB : Long,
    var registryC : Long,
    var output : String = ""
){


    fun runProgram(program : List<Int>) : List<Int> {
        var instructionPointer  = 0
        val instructions = program
        while (instructionPointer < instructions.size - 1) {
            val opcode = instructions[instructionPointer].toInt()
            val operand = instructions[instructionPointer + 1].toInt()
            instructionPointer = runInstruction(opcode, operand, instructionPointer)
        }
        return output.dropLast(1).split(",").map { it -> it.toInt() }
    }

    private fun runInstruction(opcode: Int, operand: Int, instructionPointer: Int) : Int {
        return when(opcode) {
            0 -> {
                val numerator = registryA
                val denominator = 1.shl(getOperandValue(operand, OperandType.COMBO).toInt())
                registryA = numerator / denominator
                instructionPointer + 2
            }
            1 -> {
                registryB = registryB xor getOperandValue(operand, OperandType.LITERAL)
                instructionPointer + 2
            }
            2 -> {
                registryB = getOperandValue(operand, OperandType.COMBO) % 8
                instructionPointer + 2
            }
            3 -> {
                if (registryA != 0L) {
                    getOperandValue(operand, OperandType.LITERAL).toInt()
                } else
                    instructionPointer + 2
            }
            4 -> {
                registryB = registryB xor registryC
                instructionPointer + 2
            }
            5 -> {
                output += ((getOperandValue(operand, OperandType.COMBO) % 8).toString() + ",")
                instructionPointer + 2
            }
            6 -> {
                val numerator = registryA
                val denominator = 1.shl(getOperandValue(operand, OperandType.COMBO).toInt())
                registryB = numerator / denominator
                instructionPointer + 2
            }
            7 -> {
                val numerator = registryA
                val denominator = 1.shl(getOperandValue(operand, OperandType.COMBO).toInt())
                registryC = numerator / denominator
                instructionPointer + 2
            }
            else -> throw IllegalArgumentException()
        }
    }

    private enum class OperandType() {
        COMBO, LITERAL
    }
    private fun getOperandValue(operand: Int, type: OperandType): Long {
        return if (type == OperandType.LITERAL) {
            operand.toLong()
        } else {
            when(operand) {
                0,1,2,3 -> operand.toLong()
                4 -> registryA
                5 -> registryB
                6 -> registryC
                else -> throw IllegalArgumentException()
            }
        }
    }
}