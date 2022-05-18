
fun String.lastCharByMe() = this.get(this.length - 1)

fun String.repeat(n: Int): String {
    val sb = StringBuilder(n * length)
    for (i in 1..n) {
        sb.append(this)
    }

    return sb.toString()
}

infix fun <T> T.eq(other: T) {
    if(this == other) println("OK")
    else println("Error: $this != $other")
}

fun List<Int>.sum() : Int {
    return this.reduce({ acc, current -> acc + current})
}

fun main() {
    println("abc".last())
    println("abc".lastCharByMe())
    println("abc".repeat(3))
    42 eq 42
    42 eq 43
    println(listOf(1, 2, 3).sum())
    // no sum method on list of strings: listOf("one", "two", "three").sum
}
