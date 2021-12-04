data class Bingo(var bingo: Boolean, val board: Array<Array<Pair<Int, Boolean>>>, var sum: Int = 0)

fun main() {
    fun printBoard(board: Array<Array<Pair<Int, Boolean>>>) {
        for (i in 0 until 5) {
            for (j in 0 until 5) {
                print("(${board[i][j].first},${board[i][j].second})")
                print(",")
            }
            println()
        }
        println("-------")
    }

    fun initEmptyMultiDimArray() = Array(5) {
        Array(5) {
            Pair(0, false)
        }
    }

    fun readBoards(input: List<String>): MutableList<Bingo> {
        val boards: MutableList<Bingo> = mutableListOf()
        var tempBoard: Array<Array<Pair<Int, Boolean>>> = initEmptyMultiDimArray()

        var row = 0
        for (i in 2 until input.size) {
            if (input[i].isEmpty()) {
                row = 0
                boards.add(Bingo(false, tempBoard))
                tempBoard = initEmptyMultiDimArray()
                continue
            }

            input[i].split(" ").filter { it.isNotBlank() }.map { it.toInt() }.forEachIndexed { index, number ->
                tempBoard[row][index] = tempBoard[row][index].copy(first = number)

            }
            row++
        }
        boards.add(Bingo(false, tempBoard))

        return boards
    }

    fun markDrawnNumbers(board: Array<Array<Pair<Int, Boolean>>>, drawnNumber: Int) {
        for (i in 0 until 5) {
            for (j in 0 until 5) {
                if (board[i][j].first == drawnNumber) {
                    board[i][j] =  board[i][j].copy(second = true)
                }
            }
        }
    }

    fun checkColumnBingo(board: Bingo, drawnNumber: Int): Bingo {
        for (i in 0 until 5) {
            var columnFilled = true
            for (j in 0 until 5) {
                if (!board.board[i][j].second) {
                    columnFilled = false
                }
            }
            if (columnFilled) {
                var sumOfUndrawnNumbers = 0
                for (k in 0..board.board.size-1) {
                    for (l in 0..board.board[k].size-1) {
                        if (!board.board[k][l].second) {
                            sumOfUndrawnNumbers+=board.board[k][l].first
                        }
                    }
                }
                board.bingo = true
                board.sum = sumOfUndrawnNumbers*drawnNumber
                return board.copy(bingo = true, sum = sumOfUndrawnNumbers*drawnNumber)
            }
        }
        return board
    }

    fun checkRowBingo(board: Bingo, drawnNumber: Int): Bingo {
        for (i in 0 until 5) {
            var rowFilled = true
            for (j in 0 until 5) {
                if (!board.board[j][i].second) {
                    rowFilled = false
                }
            }

            if (rowFilled) {
                var sumOfUndrawnNumbers = 0
                for (k in 0 until board.board.size) {
                    for (l in 0 until board.board[k].size) {
                        if (!board.board[k][l].second) {
                            sumOfUndrawnNumbers+=board.board[k][l].first
                        }
                    }
                }
                board.bingo = true
                board.sum = sumOfUndrawnNumbers*drawnNumber
                return board.copy(bingo = true, sum = sumOfUndrawnNumbers*drawnNumber)
            }
        }
        return board
    }

    fun part1(input: List<String>): Int {
        val drawnNumbers = input[0].split(",").map { it.toInt() }

        val boards = readBoards(input)

        for (drawnNumber in drawnNumbers) {
            for (board in boards) {
                markDrawnNumbers(board.board, drawnNumber)
            }

            for (board in boards) {
                val bingo = checkColumnBingo(board, drawnNumber)
                if (bingo.bingo) {
                    return bingo.sum
                }
            }

            for (board in boards) {
                val bingo = checkRowBingo(board, drawnNumber)
                if (bingo.bingo) {
                    return bingo.sum
                }
            }
        }

        return input.size
    }

    fun part2(input: List<String>): Int {
        val drawnNumbers = input[0].split(",").map { it.toInt() }

        val boards = readBoards(input)
        var tempBoards = boards
        for (drawnNumber in drawnNumbers) {
            for (board in tempBoards) {
                markDrawnNumbers(board.board, drawnNumber)
            }

            for (board in tempBoards) {
                val bingo = checkColumnBingo(board, drawnNumber)

                if (bingo.bingo && tempBoards.size == 1) {
                    return board.sum
                }
            }

            for (board in tempBoards) {
                val bingo = checkRowBingo(board, drawnNumber)

                if (bingo.bingo && tempBoards.size == 1) {
                    return board.sum
                }
            }

            tempBoards = tempBoards.filter { !it.bingo }.toMutableList()
        }

        return input.size
    }


    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
