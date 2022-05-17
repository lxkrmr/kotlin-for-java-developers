fun isValidIdentifier(s: String): Boolean {
    if (s.isEmpty()) return false
    if (!startsWithLetterOrUnderscore(s)) return false
    return containsOnlyValidCharacters(s)
}

fun startsWithLetterOrUnderscore(s: String): Boolean {
    val firstElement = s[0]
    return firstElement.isLetter() || isUnderscore(s[0])
}

fun containsOnlyValidCharacters(s: String): Boolean {
    for (ch in s) {
        if (!isValidChar(ch)) return false
    }

    return true
}

fun isValidChar(ch: Char): Boolean {
    return ch.isLetterOrDigit() || isUnderscore(ch)
}

fun isUnderscore(ch: Char): Boolean {
    return ch == '_'
}

fun main() {
    println(isValidIdentifier("name"))   // true
    println(isValidIdentifier("_name"))  // true
    println(isValidIdentifier("_12"))    // true
    println(isValidIdentifier(""))       // false
    println(isValidIdentifier("012"))    // false
    println(isValidIdentifier("no$"))    // false
}