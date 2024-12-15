package day15

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SolutionDay15KtTest {
    @Test
    fun partTwoSimpleCase1() {
        val map = mutableListOf(
            "####################".toMutableList(),
            "##....[]....[]..[]##".toMutableList(),
            "##............[]..##".toMutableList(),
            "##..[][]....[]..[]##".toMutableList(),
            "##....[]@.....[]..##".toMutableList(),
            "##[]##....[]......##".toMutableList(),
            "##[]....[]....[]..##".toMutableList(),
            "##..[][]..[]..[][]##".toMutableList(),
            "##........[]......##".toMutableList(),
            "####################".toMutableList()
        )
        val route = "<vv>^<v^>v>^vv^v>v<>v^v<v<^vv<<<^><<><>>v<vvv<>^v^>^<<<><<v<<<v^vv^v>^" +
                "vvv<<^>^v^^><<>>><>^<<><^vv^^<>vvv<>><^^v>^>vv<>v<<<<v<^v>^<^^>>>^<v<v" +
                "><>vv>v^v^<>><>>>><^^>vv>v<^^^>>v^v^<^^>v^^>v^<^v>v<>>v^v^<v>v^^<^^vv<" +
                "<<v<^>>^^^^>>>v^<>vvv^><v<<<>^^^vv^<vvv>^>v<^^^^v<>^>vvvv><>>v^<<^^^^^" +
                "^><^><>>><>^^<<^^v>>><^<v>^<vv>>v>>>^v><>^v><<<<v>>v<v<v>vvv>^<><<>^><" +
                "^>><>^v<><^vvv<^^<><v<<<<<><^v<<<><<<^^<v<^^^><^>>^<v^><<<^>>^v<v^v<v^" +
                ">^>>^v>vv>^<<^v<>><<><<v<<v><>v<^vv<<<>^^v^>^^>>><<^v>>v^v><^^>>^<>vv^" +
                "<><^^>^^^<><vvvvv^v<v<<>^v<v>v<<^><<><<><<<^^<<<^<<>><<><^^^>^^<>^>v<>" +
                "^^>vv<^v^v<vv>^<><v<^v>^^^>>>^^vvv^>vvv<>>>^<^>>>>>^<<^v>^vvv<>^<><<v>" +
                "v^^>>><<^^<>>^v^<v^vv<>v^<<>^<^v^v><^<<<><<^<v><v<>vv>>v><v^<vv<>v^<<^"

        val result = calculateSumOfBoxesGPSCoords(map, route)

        assertEquals(9021L, result)
    }

    @Test
    fun partTwoSimpleCase2() {
        val map = mutableListOf(
            "#######".toMutableList(),
            "#.....#".toMutableList(),
            "#.....#".toMutableList(),
            "#.@O..#".toMutableList(),
            "#..#O.#".toMutableList(),
            "#...O.#".toMutableList(),
            "#..O..#".toMutableList(),
            "#.....#".toMutableList(),
            "#######".toMutableList(),
        )
        val route = ">><vvv>v>^^^"

        val result = calculateSumOfBoxesGPSCoords(getWiderMap(map), route)

        assertEquals(1430L, result)
    }

    @Test
    fun partTwoSimpleCase3() {
        val map = mutableListOf(
            "########".toMutableList(),
            "##....##".toMutableList(),
            "##.[]###".toMutableList(),
            "####[]##".toMutableList(),
            "##..@.##".toMutableList(),
            "########".toMutableList(),
        )

        val shouldBe = mutableListOf(
            "########".toMutableList(),
            "##....##".toMutableList(),
            "##.[]###".toMutableList(),
            "####[]##".toMutableList(),
            "##....##".toMutableList(),
            "########".toMutableList(),
        )
        val route = "^^"

        val result = robotRoute(map, route)

        assertEquals(shouldBe, result)
    }
}







//
//########
//##..####
//##[]..##
//##.[].##
//##..[]##
//##..@.##
//########
//
//########
//##....##
//##.[].##
//####[]##
//##..@.##
//########