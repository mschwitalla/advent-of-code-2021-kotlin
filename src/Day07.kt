import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val horizontalPositions = input[0].split(",").map { it.toInt() }
        val maxValue = horizontalPositions.maxOf { it }
        var minFuelCosts = Int.MAX_VALUE
        for (position in 0..maxValue) {
            var tempFuelCosts = 0
            for (i in 0 until horizontalPositions.size) {
                tempFuelCosts += abs(horizontalPositions[i]-position)
            }
            if (tempFuelCosts < minFuelCosts) {
                minFuelCosts = tempFuelCosts
            }
        }

        return minFuelCosts
    }

    fun part2(input: List<String>): Int {
        val horizontalPositions = input[0].split(",").map { it.toInt() }
        val maxValue = horizontalPositions.maxOf { it }
        var minFuelCosts = Int.MAX_VALUE
        for (position in 0..maxValue) {
            var tempFuelCosts = 0
            for (i in 0 until horizontalPositions.size) {
                val delta = abs(horizontalPositions[i]-position)
                var counter = 1
                for (j in 1..delta) {
                    tempFuelCosts += counter
                    counter++
                }
            }
            if (tempFuelCosts < minFuelCosts) {
                minFuelCosts = tempFuelCosts
            }
        }

        return minFuelCosts
    }


    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
