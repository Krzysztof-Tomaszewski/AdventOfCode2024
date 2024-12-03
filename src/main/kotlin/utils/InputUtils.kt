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