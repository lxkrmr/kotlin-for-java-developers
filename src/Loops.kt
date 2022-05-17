fun main() {
    iterateOverList()
    iterateOverListWithIndex()
    iterateOverMap()
    iterateOverRange()
    iterateOverRangeExcludingUpperBound()
    iterateOverString()
}

fun iterateOverList() {
    println("iterate over list")
    val list = listOf("a", "b", "c")

    for(s in list) {
        println(s)
    }

}

fun iterateOverListWithIndex() {
    println("iterate over list with index")
    val list = listOf("a", "b", "c")

    for((index, element) in list.withIndex()) {
        println("$index: $element")
    }
}

fun iterateOverMap() {
    println("iterate over map")
    val map = mapOf(
        1 to "one",
        2 to "two",
        3 to "three"
    )

    for((key, value) in map) {
        println("$key = $value")
    }
}

fun iterateOverRange() {
    println("iterate over range")
    for(i in 1..9) {
        println(i)
    }
}

fun iterateOverRangeExcludingUpperBound() {
    println("iterate over range excluding upper bound")
    for(i in 1 until 9) {
        println(i)
    }
}

fun iterateOverString() {
    println("iterate over string")
    for(ch in "abc") {
        println(ch + 1)
    }
}
