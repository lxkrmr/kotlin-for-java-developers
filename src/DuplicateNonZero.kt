fun duplicateNonZero(list: List<Int>): List<Int> {
    return list.flatMap {
        // return in Kotlin returns for the whole function
        // this means that we are returning any an empty list
        if (it == 0) return listOf()
        listOf(it, it)
    }
}

fun duplicateNonZeroFixedByUsingLabels(list: List<Int>): List<Int> {
    return list.flatMap {
        if (it == 0) return@flatMap listOf<Int>()
        listOf(it, it)
    }
}

fun duplicateNonZeroFixedByUsingLocalFunction(list: List<Int>): List<Int> {
    fun duplicateNonZeroElement(e: Int): List<Int> {
        if (e == 0) return listOf()
        return listOf(e, e)
    }
    return list.flatMap(::duplicateNonZeroElement)
}

fun duplicateNonZeroFixedByUsingAnonymousFunction(list: List<Int>): List<Int> {
    return list.flatMap(fun (e) : List<Int> {
        if (e == 0) return listOf()
        return listOf(e, e)
    })
}

fun main() {
    val list = listOf(3, 0, 5)
    duplicateNonZero(list) eq listOf() // wrong
    duplicateNonZeroFixedByUsingLabels(list) eq listOf(3, 3, 5, 5)
    duplicateNonZeroFixedByUsingLocalFunction(list) eq listOf(3, 3, 5, 5)
    duplicateNonZeroFixedByUsingAnonymousFunction(list) eq listOf(3, 3, 5, 5)
}