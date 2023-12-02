fun main() {
    fun part1(input: List<String>): Int {
        var sum: Int = 0
        input.forEach { line ->
            var left: Int? = null
            var right: Int? = null
            var index: Int = 0
            val lineLength: Int = line.length - 1
            while (left == null || right == null) {
                if (left == null) {
                    if (line[index].isDigit()) {
                        left = line[index].digitToInt()
                    }
                }
                if (right == null) {
                    if (line[lineLength - index].isDigit()) {
                        right = line[lineLength - index].digitToInt()
                    }
                }
                index += 1
            }
            sum += 10 * left + right
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var sum: Int = 0
        val numbersDict = mapOf("one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9)
        input.forEach { line -> 
            var first: Int? = null
            var previous: Int? = null
            var potentials: MutableList<Pair<String, Int>> = mutableListOf()
            for (c in line) { 
                val newPotentials: MutableList<Pair<String, Int>> = mutableListOf()
                if (c.isDigit()) {
                    if (first == null) {
                        first = c.digitToInt()
                    }
                    previous = c.digitToInt()
                } else {
                    for (potentialPair in potentials) {
                        val (potential, original) = potentialPair
                        if (c == potential[0]) {
                            if (potential.length == 1) {
                                if (first == null) {
                                    first = original
                                }
                                previous = original
                            } else {
                                newPotentials.add(Pair(potential.substring(1), original))
                            }
                        }
                    }
                    for (number in numbersDict.keys) {
                        if (c == number[0]) {
                            newPotentials.add(Pair(number.substring(1), numbersDict[number]!!))
                        }
                    }
                }
                potentials = newPotentials
            }
            sum += 10 * first!! + previous!!
        }
        return sum
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
