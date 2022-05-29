enum class ColorRevisited {
    BLUE, ORANGE, RED
}

fun ColorRevisited.getDescription(): String =
    when (this) {
        ColorRevisited.BLUE -> "cold"
        ColorRevisited.ORANGE -> "mild"
        ColorRevisited.RED -> "hot"
    }

enum class ColorRGB(
    val r: Int, val g: Int, val b: Int
) {
    BLUE(0,0,255), ORANGE(255, 165, 0), RED(255, 0,0);

    fun rgb() = (r * 256 + g) * 256 + b
}

fun main() {
    println(ColorRevisited.BLUE.getDescription())
    println(ColorRevisited.ORANGE.getDescription())
    println(ColorRevisited.RED.getDescription())

    println(ColorRGB.BLUE.r)
    println(ColorRGB.BLUE.rgb())
}