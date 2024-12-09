package day09

import utils.getStringFromFile

fun main() {
    val map = getStringFromFile("/day09/input.txt")

    val result1 = checksumAfterCompactingByBlock(map)
    val result2 = checksumAfterCompactingByFile(map)

    println("Part 1 result: $result1")//Part 1 result: 6310675819476
    println("Part 2 result: $result2")//Part 2 result: 6335972980679
}

fun checksumAfterCompactingByBlock(map: String): Long {
    val diskMapExtended: MutableList<String> = extendDiskMap(map)

    compactByBlock(diskMapExtended)

    return calculateChecksum(diskMapExtended)

}

fun checksumAfterCompactingByFile(map: String): Long {
    val diskMapExtended: MutableList<String> = extendDiskMap(map)

    compactByFile(diskMapExtended)

    return calculateChecksum(diskMapExtended)

}

private fun extendDiskMap(map: String): MutableList<String> {
    val diskMapExtended: MutableList<String> = mutableListOf()

    for (col in map.indices) {
        if (col % 2 == 0) {
            for (i in 0..<map[col].toString().toInt()) {
                diskMapExtended.add((col / 2).toString())
            }
        } else {
            for (i in 0..<map[col].toString().toInt()) {
                diskMapExtended.add(".")
            }

        }
    }
    return diskMapExtended
}

private fun compactByBlock(diskMapExtended: MutableList<String>) {
    var left = 0
    var right = diskMapExtended.size - 1

    while (left < right) {
        while (diskMapExtended[left] != "." && left < right) {
            left++
        }
        while (diskMapExtended[right] == "." && left < right) {
            right--
        }
        if (left > right) break

        diskMapExtended[left] = diskMapExtended[right]
        diskMapExtended[right] = "."
    }
}

private fun compactByFile(diskMapExtended: MutableList<String>) {
    var right = diskMapExtended.size - 1

    while (right > 0) {
        val (fileLength, newRight) = locateFileFromRight(right, diskMapExtended)
        right = newRight

        val blankSpaceStart: Int = locateLeftmostFreeSpace(right, diskMapExtended, fileLength) ?: continue

        moveFileToFreeSpace(fileLength, diskMapExtended, blankSpaceStart, right)
    }
}

private fun moveFileToFreeSpace(
    fileLength: Int,
    diskMapExtended: MutableList<String>,
    blankSpaceStart: Int,
    right: Int
) {
    for (i in 0..<fileLength) {
        diskMapExtended[blankSpaceStart!! + i] = diskMapExtended[right + 1 + i]
        diskMapExtended[right + 1 + i] = "."
    }
}

private fun locateFileFromRight(
    right: Int,
    diskMapExtended: MutableList<String>
): Pair<Int, Int> {
    var right1 = right
    var fileLength = 0
    var fileBlock = ""
    var fileFound = false

    while (!fileFound && right1 >= 0) {

        if (fileBlock.isEmpty()) {
            if (diskMapExtended[right1] == ".") {
                right1--
            } else {
                fileBlock = diskMapExtended[right1]
                fileLength++
                right1--
            }
        } else {
            if (diskMapExtended[right1] == fileBlock) {
                fileLength++
                right1--
            } else {
                fileFound = true
            }
        }
    }
    return Pair(fileLength, right1)
}

private fun locateLeftmostFreeSpace(
    right: Int,
    diskMapExtended: MutableList<String>,
    fileLength: Int
): Int? {
    var left = 0
    var blankSpaceLength = 0
    var blankSpaceStart: Int? = null

    while (blankSpaceStart == null && left <= right) {

        if (diskMapExtended[left] != ".") {
            blankSpaceLength = 0
        } else {
            blankSpaceLength++
            if (blankSpaceLength == fileLength) {
                blankSpaceStart = left - blankSpaceLength + 1
            }
        }
        left++
    }
    return blankSpaceStart
}

private fun calculateChecksum(diskMapExtended: MutableList<String>): Long {
    var checksum = 0L
    for (i in diskMapExtended.indices) {
        if (diskMapExtended[i] != ".") {
            checksum += (i * diskMapExtended[i].toLong())
        }
    }
    println(diskMapExtended)
    return checksum
}