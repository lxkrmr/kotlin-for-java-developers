fun main() {
    val sb = StringBuilder()
    // if the lambda is the last argument then it can be moved outside the brackets
    val result = with(sb) {
        // inside we have an implicit sb as this
        // so appendLine for example means this.appendLine => sb.appendLine
        appendLine("Alphabet: ")
        for (c in 'a'..'z') {
            append(c)
        }
        this.toString()
    }
    println(result)

    // or
    val secondResult = buildString {
        appendLine("Another alphabet: ")
        for (c in 'a'..'z') {
            append(c)
        }
        this.toString()
    }
    println(secondResult)
}