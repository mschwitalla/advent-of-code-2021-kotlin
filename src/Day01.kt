fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }.mapIndexed { index, depth ->
            if (index == 0) {
                0
            } else if (input[index - 1].toInt() < depth) {
                1
            } else {
                0
            }
        }.sum()
    }

    fun part2(input: List<String>): Int {
        var result = 0
        for (i in 0..input.size - 4) {
            val firstWindowSum = input.subList(i, i + 3).sumOf { it.toInt() }
            val secondWindowSum = input.subList(i + 1, i + 4).sumOf { it.toInt() }

            if (firstWindowSum < secondWindowSum) {
                result++
            }
        }
        return result
    }


    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
