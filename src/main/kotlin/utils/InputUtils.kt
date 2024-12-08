package utils

fun getTwoIntListsFromFile(filePath: String): Pair<List<Int>, List<Int>> {
    val inputStream = {}.javaClass.getResourceAsStream(filePath)
        ?: throw IllegalArgumentException("File not found in resources")

    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()

    // Read and parse the file
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            val parts = line.trim().split("\\s+".toRegex())
            if (parts.size == 2) {
                val num1 = parts[0].toIntOrNull()
                val num2 = parts[1].toIntOrNull()
                if (num1 != null && num2 != null) {
                    list1.add(num1)
                    list2.add(num2)
                }
            }
        }
    }
    return Pair(list1, list2)
}

fun getIntPairsAndIntListsFromFile(filePath: String): Pair<List<Pair<Int,Int>>,List<List<Int>>> {
    val inputStream = {}.javaClass.getResourceAsStream(filePath)
        ?: throw IllegalArgumentException("File not found in resources")

    val intsList = mutableListOf<List<Int>>()
    val intPairs = mutableListOf<Pair<Int,Int>>()

    var firstPart = true

    // Read and parse the file
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            if (line.trim().isEmpty()) {
                firstPart = false
            } else {
                if(firstPart) {
                    val split = line.split("|")
                    intPairs.add(split[0].toInt() to split[1].toInt())
                } else {
                    val split = line.split(",")
                    intsList.add(split.map { s -> s.toInt() })
                }
            }
        }
    }
    return intPairs to intsList
}

fun getStringListFromFile(filePath: String): List<String> {
    val inputStream = {}.javaClass.getResourceAsStream(filePath)
        ?: throw IllegalArgumentException("File not found in resources")

    val list = mutableListOf<String>()

    // Read and parse the file
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            list.add(line)
        }
    }
    return list
}

fun getListOfCharListsFromFile(filePath: String): MutableList<MutableList<Char>> {
    val inputStream = {}.javaClass.getResourceAsStream(filePath)
        ?: throw IllegalArgumentException("File not found in resources")

    val list = mutableListOf<MutableList<Char>>()

    // Read and parse the file
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            list.add(line.trim().toMutableList())
        }
    }
    return list
}

fun getListOfIntListsFromFile(filePath: String): List<List<Int>> {
    val inputStream = {}.javaClass.getResourceAsStream(filePath)
        ?: throw IllegalArgumentException("File not found in resources")

    val list = mutableListOf<List<Int>>()

    // Read and parse the file
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            val parts = line.trim().split("\\s+".toRegex())
            if (parts.isNotEmpty()) {
                val intList = parts.map { e -> e.toInt() }
                list.add(intList)
            }
        }
    }
    return list
}

fun getIntAndListOfIntsPairsFromFile(filePath: String): List<Pair<Long,List<Long>>> {
    val inputStream = {}.javaClass.getResourceAsStream(filePath)
        ?: throw IllegalArgumentException("File not found in resources")

    val list = mutableListOf<Pair<Long,List<Long>>>()

    // Read and parse the file
    inputStream.bufferedReader().useLines { lines ->
        lines.forEach { line ->
            val split = line.split(": ")
            list.add(split[0].toLong() to (split[1].split(" ").toList().map { i -> i.toLong() }))
        }
    }
    return list
}