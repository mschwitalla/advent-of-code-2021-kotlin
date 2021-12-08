import kotlin.math.abs
import kotlin.math.max

data class Line(val x1: Int, val y1: Int, val x2: Int, val y2: Int)
data class Function(val m: Int, val b: Int)

fun main() {
    fun calculateFunction(line: Line, pitch: Int): Function {
        val b  = line.y1 - (pitch*line.x1)
        return Function(pitch, b)
    }

    fun calculatePitch(line: Line): Int {
        return (line.x2-line.x1).div(line.y2-line.y1)
    }

    fun isDiagonal(line: Line): Boolean {
        if (abs((line.x2-line.x1).div(line.y2-line.y1)) == 1) {
            return true
        }
        return false
    }

    fun part1(input: List<String>): Int {
        var maxValueX = 0
        var maxValueY = 0

        val lines = input.map {
            val coordinates = it.split(" -> ")
            val firstCoordinate = coordinates.first().split(",")
            val secondCoordinate = coordinates[1].split(",")

            val x1 = firstCoordinate[0].toInt()
            val x2 = secondCoordinate[0].toInt()

            val y1 = firstCoordinate[1].toInt()
            val y2 = secondCoordinate[1].toInt()

            val maxX = max(x1, x2)
            val maxY = max(y1, y2)
            if (maxValueX < maxX) {
                maxValueX = maxX
            }

            if (maxValueY < maxY) {
                maxValueY = maxY
            }

            Line(x1, y1, x2, y2)
        }

        val diagram = Array(maxValueX+1) {
            Array(maxValueY+1) {
                0
            }
        }

        for (line in lines) {
            if (line.x1 == line.x2) {
                if (line.y2 < line.y1) {
                    for (i in line.y1 downTo line.y2) {
                        diagram[line.x1][i] += 1
                    }
                } else {
                    for (i in line.y1..line.y2) {
                        diagram[line.x1][i] += 1
                    }
                }
            } else if (line.y1 == line.y2) {
                if (line.x2 < line.x1) {
                    for (i in line.x1 downTo line.x2) {
                        diagram[i][line.y1] += 1
                    }
                } else {
                    for (i in line.x1..line.x2) {
                        diagram[i][line.y1] += 1
                    }
                }
            }
        }

        var result = 0
        for (i in 0 until diagram.size) {
            for (j in 0 until diagram[i].size) {
                if (diagram[j][i] == 0) {
                    print(".")
                } else {
                    print("${diagram[j][i]}")
                }
                if (diagram[i][j] >= 2) {
                    result++
                }
            }
            println()
        }

        return result
    }

    fun part2(input: List<String>): Int {
        var maxValueX = 0
        var maxValueY = 0

        val lines = input.map {
            val coordinates = it.split(" -> ")
            val firstCoordinate = coordinates.first().split(",")
            val secondCoordinate = coordinates[1].split(",")

            val x1 = firstCoordinate[0].toInt()
            val x2 = secondCoordinate[0].toInt()

            val y1 = firstCoordinate[1].toInt()
            val y2 = secondCoordinate[1].toInt()

            val maxX = max(x1, x2)
            val maxY = max(y1, y2)
            if (maxValueX < maxX) {
                maxValueX = maxX
            }

            if (maxValueY < maxY) {
                maxValueY = maxY
            }

            val line = Line(x1, y1, x2, y2)
            line
        }

        val diagram = Array(maxValueX+1) {
            Array(maxValueY+1) {
                0
            }
        }

        for (line in lines) {
            if (line.x1 == line.x2) {
                if (line.y2 < line.y1) {
                    for (i in line.y1 downTo line.y2) {
                        diagram[line.x1][i] += 1
                    }
                } else {
                    for (i in line.y1..line.y2) {
                        diagram[line.x1][i] += 1
                    }
                }
            } else if (line.y1 == line.y2) {
                if (line.x2 < line.x1) {
                    for (i in line.x1 downTo line.x2) {
                        diagram[i][line.y1] += 1
                    }
                } else {
                    for (i in line.x1..line.x2) {
                        diagram[i][line.y1] += 1
                    }
                }
            } else if (isDiagonal(line)) {
                val pitch = calculatePitch(line)
                val function = calculateFunction(line, pitch)

                if (line.x1 < line.x2) {
                    for (i in line.x1..line.x2) {
                        diagram[i][i*function.m+function.b] += 1
                    }
                } else if (line.x1 > line.x2) {
                    for (i in line.x1 downTo line.x2) {
                        diagram[i][i*function.m+function.b] += 1
                    }
                }
            }
        }

        var result = 0
        for (i in 0 until diagram.size) {
            for (j in 0 until diagram[i].size) {
                if (diagram[j][i] == 0) {
                    print(". ")
                } else {
                    print("${diagram[j][i]} ")
                }
                if (diagram[i][j] >= 2) {
                    result++
                }
            }
            println()
        }

        return result
    }


    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
