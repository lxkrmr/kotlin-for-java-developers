fun main() {
    val list1 = mutableListOf(1, 2, 3)
    val list2 = list1
    list2 += 4
    println(list1)
    println(list2)
}