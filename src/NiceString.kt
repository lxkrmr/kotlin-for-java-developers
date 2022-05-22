fun main() {
    "bac".doesNotContainSubstring() eq false
    "aaa".doesNotContainSubstring() eq true

    "bac".containsAtLeastThreeVowels() eq false
    "aaa".containsAtLeastThreeVowels() eq true

    "bac".containsADoubleLetter() eq false
    "aaa".containsADoubleLetter() eq true

    "bac".isNice() eq false
    "aaa".isNice() eq true

    "abaca".isNice() eq false
}

fun String.isNice(): Boolean {
    return listOf(
        this.doesNotContainSubstring(),
        this.containsAtLeastThreeVowels(),
        this.containsADoubleLetter()
    ).count { it } >= 2
}

fun String.doesNotContainSubstring(): Boolean {
    return listOf("bu", "ba", "be").none { it in this }
}

fun String.containsAtLeastThreeVowels(): Boolean {
    return this.count { it in listOf('a', 'e', 'i', 'o', 'u') } >= 3
}

fun String.containsADoubleLetter(): Boolean {
    for(index in 0 until this.length) {
        if (this.getOrNull(index) == this.getOrNull(index + 1)) return true
    }

    return false
}
