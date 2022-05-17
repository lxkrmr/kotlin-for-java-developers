
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

fun main() {
    println("abc".last())
    println("abc".lastCharByMe())
    println("abc".repeat(3))
    42 eq 42
    42 eq 43
}
