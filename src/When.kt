import Color.*

enum class Color {
    BLUE, ORANGE, RED
}

fun getDescription(color: Color): String =
    when(color) {
        BLUE -> "cold"
        ORANGE -> "mild"
        RED -> "hot"
    }

fun main() {
    println(getDescription(BLUE))
    println(getDescription(ORANGE))
    println(getDescription(RED))
}