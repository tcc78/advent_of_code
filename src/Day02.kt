fun main() {
    fun part1(input: List<String>): Int {
        var currentGame: Int = 1
        var answer: Int = 0
        val regex = Regex("""(\d+) (\w+)""")
        for (line in input) {
            val sets = line.split(";")
            var setGood = true
            for (set in sets) {
                if (!setGood) {
                    break
                }
                val numbers = regex.findAll(set)
                for (number in numbers) {
                    val (num, color) = number.destructured
                    if (color == "red" && num.toInt() > 12) {
                        setGood = false
                        break
                    }
                    if (color == "green" && num.toInt() > 13) {
                        setGood = false
                        break
                    }
                    if (color == "blue" && num.toInt() > 14) {
                        setGood = false
                        break
                    }
                }
            }
            if (setGood) {
                answer += currentGame
            }
            currentGame += 1
        }
        return answer
    }

    fun part2(input: List<String>): Int {
        var answer: Int = 0
        val regex = Regex("""(\d+) (\w+)""")
        for (line in input) {
            val sets = line.split(";")
            var colorDict: MutableMap<String, Int> = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
            for (set in sets) {
                val numbers = regex.findAll(set)
                for (number in numbers) {
                    val (num, color) = number.destructured
                    colorDict[color] = maxOf(colorDict[color]!!, num.toInt())
                }
            }
            answer += colorDict["red"]!! * colorDict["green"]!! * colorDict["blue"]!!
        }
        return answer
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
