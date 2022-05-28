val String.lastIndex: Int
    get() = if (isNotEmpty()) length - 1 else 0

val String.indices: IntRange
    get() = 0..lastIndex

val String.medianChar
    get(): Char? {
        println("Calculating....")
        return getOrNull(length / 2)
    }

fun main() {
    println("foo".lastIndex)
    println("".lastIndex)
    println("foo".indices)
    println("".indices)

    val s = "abc"
    println(s.medianChar)
    println(s.medianChar)
}