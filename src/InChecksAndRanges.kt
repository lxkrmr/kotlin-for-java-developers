fun main() {
    println(isLetter('a'))
    println(isNotDigit('x'))
    println(recognize('$'))
    println(rangesAndStrings())
    println(isElementInCollection(1))
}

fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'

fun isNotDigit(c: Char): Boolean = c !in '0'..'9'

fun recognize(c: Char): String = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter"
    else -> "I don't know"
}

fun rangesAndStrings(): Boolean = "Kotlin" in "Java" .. "Scala"

fun isElementInCollection(element: Int): Boolean {
    val list = listOf(1, 2, 3)

    return element in list
}
