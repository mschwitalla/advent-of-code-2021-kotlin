fun main() {
    fun part1(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        input.map { it.split(" ") }.forEach {
            val value = it[1].toInt()
            when (it.first()) {
                "forward" -> horizontalPosition += value
                "down" -> depth += value
                "up" -> depth -= value
            }
        }
        return horizontalPosition * depth
    }

    fun part2(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0
        input.map { it.split(" ") }.forEach {
            val value = it[1].toInt()
            when (it.first()) {
                "forward" -> {
                    horizontalPosition += value
                    depth += aim * value
                }
                "down" -> aim += value
                "up" -> aim -= value
            }
        }
        return horizontalPosition * depth
    }


    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
