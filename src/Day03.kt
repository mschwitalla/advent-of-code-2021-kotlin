import kotlin.math.pow

fun main() {
    fun calculateDecimal(binary: String): Int {
        var decimal = 0
        binary.reversed().forEachIndexed { index, char ->
            if (index == 0) {
                if (char == '1') {
                    decimal += 1
                }
            } else {
                decimal += (char.toString().toDouble() * 2.0.pow(index.toDouble())).toInt()
            }
        }
        return decimal
    }

    fun part1(input: List<String>): Int {
        var gammaRate = ""
        var epsilonRate = ""

        val array = intArrayOf(*(input[0].map { 0 }.toIntArray()))

        input.forEach { bitSequence ->
            bitSequence.forEachIndexed { index, char ->
                if (char == '1') {
                    array[index]++
                }
            }
        }

        array.forEach {
            gammaRate += if (it > input.size.div(2.0)) {
                "1"
            } else {
                "0"
            }
        }

        array.forEach {
            epsilonRate += if (it < input.size.div(2.0)) {
                "1"
            } else {
                "0"
            }
        }

        val gammaRateDecimal = calculateDecimal(gammaRate)
        val epsilonRateDecimal = calculateDecimal(epsilonRate)

        return gammaRateDecimal * epsilonRateDecimal
    }

    fun part2(input: List<String>): Int {
        var countedOnes = 0
        var indexPosition = 0
        var temp = input
        while (temp.size > 1) {
            temp.forEach {
                if (it[indexPosition] == '1') {
                    countedOnes++
                }
            }

            temp = if (temp.size.div(2.0) < countedOnes.toDouble()) {
                temp.filter { it[indexPosition] == '1' }
            } else if (temp.size.div(2.0) == countedOnes.toDouble()) {
                temp.filter { it[indexPosition] == '1' }
            } else {
                temp.filter { it[indexPosition] == '0' }
            }

            countedOnes = 0
            indexPosition++
        }

        val oxygenGeneratorRating = calculateDecimal(temp[0])

        countedOnes = 0
        indexPosition = 0
        temp = input
        while (temp.size > 1) {
            temp.forEach {
                if (it[indexPosition] == '1') {
                    countedOnes++
                }
            }

            temp = if (temp.size.div(2.0) < countedOnes.toDouble()) {
                temp.filter { it[indexPosition] == '0' }
            } else if (temp.size.div(2.0) == countedOnes.toDouble()) {
                temp.filter { it[indexPosition] == '0' }
            } else {
                temp.filter { it[indexPosition] == '1' }
            }

            countedOnes = 0
            indexPosition++
        }

        val co2ScrubberRating = calculateDecimal(temp[0])

        return oxygenGeneratorRating * co2ScrubberRating
    }


    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
