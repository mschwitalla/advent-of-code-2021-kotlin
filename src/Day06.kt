fun main() {
    fun part1(input: List<String>): Int {
        val fish = mutableListOf<Int>()
        input.forEach { line ->
            line.split(",").forEach {
                fish.add(it.toInt())
            }
        }

        for (days in 1..80) {
            for (i in 0 until fish.size) {
                if (fish[i] == 0) {
                    fish.add(8)
                    fish[i] = 6
                } else {
                    fish[i]--
                }
            }
        }

        return fish.size
    }

    fun part2(input: List<String>): Long {
        val fish = LongArray(9) {
            0
        }

        input.forEach { line ->
            line.split(",").forEach {
                val daysToMate = it.toInt()
                fish[daysToMate]++
            }
        }

        for (days in 1..256) {
            var tempFish = 0L
            var tempFish2: Long
            for (i in fish.size-1 downTo 0) {
                when (i) {
                    8 -> {
                        tempFish = fish[i-1]
                        fish[i-1] = fish[i]
                        fish[i] = 0
                    }
                    0 -> {
                        fish[8] = tempFish
                        fish[6] += tempFish
                    }
                    else -> {
                        tempFish2 = fish[i-1]
                        fish[i-1] = tempFish
                        tempFish = tempFish2
                    }
                }
            }
        }

        return fish.sum()
    }


    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
