fun main() {
    val board : Board = Board(1,2, 'a')
    board[1,2] = 'x'
}

data class Board(val x: Int, val y: Int, val value: Char) {}

operator fun Board.set(x: Int, y: Int, value: Char) {}

