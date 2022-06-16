// example from https://www.youtube.com/watch?v=1VWYP3S12Do "Factory Design Pattern in Kotlin"
// Chess Game
// Load state of Game

val notation = listOf("pa3", "qc5")

sealed class Piece(val position: String) {
    // option 2: static factory method
    companion object {
        fun fromNotation(notation: String): Piece {
            val pieceType = notation[0]
            val position = notation.drop(1)
            return when (pieceType) {
                'p' -> Pawn(position)
                'q' -> Queen(position)
                else -> error("Illegal piece type: $pieceType")
            }
        }
    }
}
class Pawn(position: String): Piece(position)
class Queen(position: String): Piece(position)

// option 1: Factory method
fun generatePieces(notation: List<String>): List<Piece> {
    return notation.map {
        val pieceType = it[0]
        val position = it.drop(1)
        when (pieceType) {
            'p' -> Pawn(position)
            'q' -> Queen(position)
            else -> error("Illegal piece type: $pieceType")
        }
    }
}

fun main() {
    val resultsOption1 = generatePieces(notation)
    (resultsOption1[0] is Pawn) eq true
    resultsOption1[0].position eq "a3"
    (resultsOption1[1] is Queen) eq true
    resultsOption1[1].position eq "c5"

    val resultsOption2 = notation.map { Piece.fromNotation(it) }
    (resultsOption2[0] is Pawn) eq true
    resultsOption2[0].position eq "a3"
    (resultsOption2[1] is Queen) eq true
    resultsOption2[1].position eq "c5"
}