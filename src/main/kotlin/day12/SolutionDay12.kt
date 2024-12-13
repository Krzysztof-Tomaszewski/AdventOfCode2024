package day12

import utils.getListOfCharListsFromFile

private operator fun <E> List<List<E>>.get(pair: Pair<Int, Int>): E {
    return this[pair.first][pair.second]
}

private operator fun Pair<Int, Int>.plus(pair: Pair<Int, Int>): Pair<Int, Int> {
    return (this.first + pair.first) to (this.second + pair.second)
}

fun main() {
    val gardenMap = getListOfCharListsFromFile("/day12/input.txt")

    val result1 = calculatePriceOfFencingAllRegions(gardenMap)
    val result2 = calculatePriceOfFencingAllRegionsWithDiscount(gardenMap)
    println("Part 1 result: $result1")//Part 1 result: 1437300
    println("Part 2 result: $result2")//Part 2 result:
}

fun calculatePriceOfFencingAllRegionsWithDiscount(gardenMap: MutableList<MutableList<Char>>): Long {
    var totalPrice = 0L
    val visited = mutableSetOf<Pair<Int, Int>>()


    fun bfs(x: Int, y: Int, plantType: Char): Pair<MutableList<Pair<Int, Int>>, Int> {
        val queue: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
        queue.addLast(Pair(x, y))
        val region = mutableListOf<Pair<Int, Int>>()
        var walls = 0
        val countedWalls = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()

        val directions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))

        fun wallWasCountedAlready(edge: Pair<Pair<Int, Int>, Pair<Int, Int>>, direction: Pair<Int, Int>): Boolean {
            val (x1, y1) = edge.first
            val (x2, y2) = edge.second
            return if (direction == 1 to 0 || direction == -1 to 0) {
                countedWalls.contains((x1 to y1 - 1) to (x2 to y2 - 1)) || countedWalls.contains((x1 to y1 + 1) to (x2 to y2 + 1))
            } else {
                countedWalls.contains((x1 - 1 to y1) to (x2 - 1 to y2)) || countedWalls.contains((x1 + 1 to y1) to (x2 + 1 to y2))
            }
        }

        while (queue.isNotEmpty()) {
            val plant = queue.removeFirst()
            if (plant in visited) continue
            visited.add(plant)
            region.add(plant)

            for (direction in directions) {
                val neighbour = plant + direction
                val edge = plant to neighbour
                if (neighbour.first !in gardenMap.indices || neighbour.second !in gardenMap[0].indices || gardenMap[neighbour] != plantType) {
                    if (!wallWasCountedAlready(edge, direction)) {
                        walls++
                    }
                    countedWalls.add(edge)
                } else if (neighbour !in visited) {
                    queue.add(neighbour)
                }
            }
        }
        return Pair(region, walls)
    }

    for (row in gardenMap.indices) {
        for (col in gardenMap[0].indices) {
            if (Pair(row, col) !in visited) {
                val plantType = gardenMap[row][col]
                val (region, borders) = bfs(row, col, plantType)
                val price = region.size * borders
                totalPrice += price.toLong()
            }
        }
    }
    return totalPrice
}

fun calculatePriceOfFencingAllRegions(gardenMap: MutableList<MutableList<Char>>): Long {
    var totalPrice = 0L
    val visited = mutableSetOf<Pair<Int, Int>>()


    fun bfs(x: Int, y: Int, plantType: Char): Pair<MutableList<Pair<Int, Int>>, List<Int>> {
        val queue: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
        queue.addLast(Pair(x, y))
        val region = mutableListOf<Pair<Int, Int>>()
        val outsideBorders = mutableListOf<Int>()

        while (queue.isNotEmpty()) {
            val plant = queue.removeFirst()
            if (plant in visited) continue
            visited.add(plant)
            region.add(plant)

            val directions = listOf(Pair(-1, 0), Pair(1, 0), Pair(0, -1), Pair(0, 1))
            var borders = 0
            for (direction in directions) {
                val neighbour = plant + direction
                if (neighbour.first in gardenMap.indices && neighbour.second in gardenMap[0].indices) {
                    if (gardenMap[neighbour] != plantType) {
                        borders++
                    } else if (neighbour !in visited) {
                        queue.add(neighbour)
                    }
                } else {
                    borders++
                }
            }
            outsideBorders.add(borders)
        }
        return Pair(region, outsideBorders)
    }

    for (row in gardenMap.indices) {
        for (col in gardenMap[0].indices) {
            if (Pair(row, col) !in visited) {
                val plantType = gardenMap[row][col]
                val (region, borders) = bfs(row, col, plantType)
                val bordersSum = borders.sum()
                val price = region.size * bordersSum
                totalPrice += price.toLong()
            }
        }
    }
    return totalPrice
}