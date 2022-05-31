fun <T> foo(list: List<T>) {
    for (element in list) {
        println(element)
    }
}

// Non-nullable upper bound
fun <T: Any> bar(list: List<T>) {
    for (element in list) {
        println(element)
    }
}

fun <T: Number?> oneHalf(value: T) : Double? {
    if(value == null) return null
    return value.toDouble() / 2.0
}

fun main() {
    foo(listOf(null))
    // bar(listOf(null)) --> not possible as null is "Nothing" but bar expects "Any"
}