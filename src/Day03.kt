fun main() {
    fun part1And2(input: List<String>): List<Int> {
        var schematic: MutableList<CharArray> = mutableListOf()
        for (line in input) {
            schematic.add(line.toCharArray())
        }
        var answerPart1: Int = 0
        var answerPart2: Int = 0
        val gearMap: MutableMap<Pair<Int, Int>, MutableList<Int>> = mutableMapOf()
        for (i in 0..schematic.size-1) {
            var currNum: Int = 0
            var partNum: Boolean = false
            var encounteredGears: MutableSet<Pair<Int, Int>> = mutableSetOf()
            for (j in 0..schematic[0].size-1) {
                if (schematic[i][j].isDigit()) {
                    currNum = currNum*10 + schematic[i][j].digitToInt()
                    for (x in -1..1) {
                        for (y in -1..1) {
                            if (i+x >= 0 && i+x < schematic.size && 
                                j+y >= 0 && j+y < schematic[0].size) {
                                if (schematic[i+x][j+y] != ".".get(0) && !schematic[i+x][j+y].isDigit()) {
                                    if (schematic[i+x][j+y] == "*".get(0)) {
                                        encounteredGears.add(Pair(i+x, j+y))
                                    }
                                    partNum = true
                                }
                            } 
                        }
                    }
                } else {
                    if (partNum) {
                        answerPart1 += currNum
                        for (gear in encounteredGears) {
                            if (gear in gearMap) {
                                gearMap[gear]!!.add(currNum)
                            } else {
                                gearMap.put(gear, mutableListOf(currNum))
                            }
                        }
                    }
                    currNum = 0
                    partNum = false
                    encounteredGears = mutableSetOf()
                }
            }
            if (partNum) {
                answerPart1 += currNum
                for (gear in encounteredGears) {
                    if (gear in gearMap) {
                        gearMap[gear]!!.add(currNum)
                    } else {
                        gearMap.put(gear, mutableListOf(currNum))
                    }
                }
            }
        }
        for (gear in gearMap.keys) {
            if (gearMap[gear]!!.size == 2) {
                answerPart2 += gearMap[gear]!![0] * gearMap[gear]!![1]
            }
        }
        return listOf(answerPart1, answerPart2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val testOutput = part1And2(testInput)
    check(testOutput[0] == 4361)
    check(testOutput[1] == 467835) 

    val input = readInput("Day03")
    val answer = part1And2(input)
    answer[0].println()
    answer[1].println()
}
